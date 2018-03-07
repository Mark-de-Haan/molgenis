package org.molgenis.questionnaires.service.impl;

import org.molgenis.core.ui.controller.StaticContentService;
import org.molgenis.data.DataService;
import org.molgenis.data.Entity;
import org.molgenis.data.EntityManager;
import org.molgenis.data.meta.model.Attribute;
import org.molgenis.data.meta.model.EntityType;
import org.molgenis.data.meta.model.EntityTypeMetadata;
import org.molgenis.data.security.EntityTypeIdentity;
import org.molgenis.data.security.EntityTypePermission;
import org.molgenis.questionnaires.meta.Questionnaire;
import org.molgenis.questionnaires.meta.QuestionnaireFactory;
import org.molgenis.questionnaires.meta.QuestionnaireStatus;
import org.molgenis.questionnaires.model.CategoricalOption;
import org.molgenis.questionnaires.model.QuestionnaireChapter;
import org.molgenis.questionnaires.model.QuestionnaireMeta;
import org.molgenis.questionnaires.model.QuestionnaireReference;
import org.molgenis.questionnaires.response.QuestionnaireListItemResponse;
import org.molgenis.questionnaires.response.QuestionnaireResponse;
import org.molgenis.questionnaires.service.QuestionnaireService;
import org.molgenis.security.core.UserPermissionEvaluator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.collect.Streams.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.molgenis.data.EntityManager.CreationMode.POPULATE;
import static org.molgenis.data.meta.model.EntityTypeMetadata.ENTITY_TYPE_META_DATA;
import static org.molgenis.data.security.owned.OwnedEntityType.OWNER_USERNAME;
import static org.molgenis.data.support.QueryImpl.EQ;
import static org.molgenis.questionnaires.meta.QuestionnaireMetaData.QUESTIONNAIRE;
import static org.molgenis.questionnaires.meta.QuestionnaireStatus.NOT_STARTED;
import static org.molgenis.questionnaires.meta.QuestionnaireStatus.OPEN;
import static org.molgenis.security.core.utils.SecurityUtils.getCurrentUsername;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService
{
	private static final String DEFAULT_SUBMISSION_TEXT = "<h3>Thank you for submitting the questionnaire.</h3>";

	private final DataService dataService;
	private final EntityManager entityManager;
	private final UserPermissionEvaluator userPermissionEvaluator;
	private final QuestionnaireFactory questionnaireFactory;
	private final StaticContentService staticContentService;

	public QuestionnaireServiceImpl(DataService dataService, EntityManager entityManager,
			UserPermissionEvaluator userPermissionEvaluator, QuestionnaireFactory questionnaireFactory,
			StaticContentService staticContentService)
	{
		this.dataService = Objects.requireNonNull(dataService);
		this.entityManager = requireNonNull(entityManager);
		this.userPermissionEvaluator = requireNonNull(userPermissionEvaluator);
		this.questionnaireFactory = requireNonNull(questionnaireFactory);
		this.staticContentService = requireNonNull(staticContentService);
	}

	@Override
	public List<QuestionnaireListItemResponse> getQuestionnaires()
	{
		return dataService.query(ENTITY_TYPE_META_DATA, EntityType.class)
						  .eq(EntityTypeMetadata.EXTENDS, QUESTIONNAIRE)
						  .findAll()
						  .filter(entityType -> userPermissionEvaluator.hasPermission(
								  new EntityTypeIdentity(entityType.getId()), EntityTypePermission.WRITE))
						  .map(this::createQuestionnaireListItemResponse)
						  .collect(toList());
	}

	@Override
	public QuestionnaireResponse getQuestionnaire(String id)
	{
		Questionnaire questionnaire = findQuestionnaireEntity(id);
		if (questionnaire == null)
		{
			EntityType questionnaireEntityType = dataService.getEntityType(id);
			questionnaire = questionnaireFactory.create(entityManager.create(questionnaireEntityType, POPULATE));
			questionnaire.setOwner(getCurrentUsername());
			questionnaire.setStatus(OPEN);
			dataService.add(id, questionnaire);
		}
		return createQuestionnaireResponse(questionnaire);
	}

	@Override
	public String getQuestionnaireSubmissionText(String id)
	{
		String key = id + "_submissionText";
		String submissionText = staticContentService.getContent(key);

		if (submissionText == null)
		{
			submissionText = DEFAULT_SUBMISSION_TEXT;
			staticContentService.submitContent(key, submissionText);
		}
		return submissionText;
	}

	/**
	 * Creates a {@link QuestionnaireListItemResponse} based on an {@link EntityType} of class {@link Questionnaire}
	 * Status is set to NOT_STARTED if there is no data entry available for the current user.
	 *
	 * @param entityType
	 * @return A {@link QuestionnaireListItemResponse}
	 */
	private QuestionnaireListItemResponse createQuestionnaireListItemResponse(EntityType entityType)
	{
		String questionnaireId = entityType.getId();
		QuestionnaireStatus questionnaireStatus = NOT_STARTED;

		Questionnaire questionnaire = findQuestionnaireEntity(questionnaireId);
		if (questionnaire != null)
		{
			questionnaireStatus = questionnaire.getStatus();
		}

		return QuestionnaireListItemResponse.create(questionnaireId, entityType.getLabel(), entityType.getDescription(),
				questionnaireStatus);
	}

	/**
	 * Find 1 row in the Questionnaire table that belongs to the current user
	 *
	 * @param entityTypeId The ID of a questionnaire table
	 * @return An {@link Entity} of type {@link Questionnaire}
	 */
	private Questionnaire findQuestionnaireEntity(String entityTypeId)
	{
		return questionnaireFactory.create(dataService.findOne(entityTypeId, EQ(OWNER_USERNAME, getCurrentUsername())));
	}

	/**
	 * Create a {@link QuestionnaireResponse} based on a questionnaire entity
	 *
	 * @param questionnaire A {@link Questionnaire} entity
	 * @return A {@link QuestionnaireResponse}
	 */
	private QuestionnaireResponse createQuestionnaireResponse(Questionnaire questionnaire)
	{
		QuestionnaireMeta metadata = getQuestionnaireMetadata(questionnaire);
		return QuestionnaireResponse.create(questionnaire.getIdValue().toString(), questionnaire.getLabel(),
				questionnaire.getDescription(), metadata, questionnaire);
	}

	/**
	 * Create a {@link QuestionnaireMeta} response based on a questionnaire entity
	 * Creates a list of {@link QuestionnaireChapter}
	 *
	 * @param questionnaire
	 * @return A {@link QuestionnaireMeta} auto value object
	 */
	private QuestionnaireMeta getQuestionnaireMetadata(Questionnaire questionnaire)
	{
		List<QuestionnaireChapter> chapters = stream(questionnaire.getEntityType().getAttributes()).map(
				this::createQuestionnaireChapter).collect(toList());
		return QuestionnaireMeta.create(chapters);
	}

	/**
	 * Create a {@link QuestionnaireChapter} based on a {@link Attribute}
	 *
	 * @param attribute A {@link Attribute}
	 * @return A {@link QuestionnaireChapter}
	 */
	private QuestionnaireChapter createQuestionnaireChapter(Attribute attribute)
	{
		return QuestionnaireChapter.builder()
								   .setName(attribute.getName())
								   .setLabel(attribute.getLabel())
								   .setDescription(attribute.getDescription())
								   .setFieldType(attribute.getDataType().toString())
								   .setReadOnly(attribute.isReadOnly())
								   .setVisible(attribute.isVisible())
								   .setNillable(attribute.isNillable())
								   .setVisibleExpression(attribute.getVisibleExpression())
								   .setNullableExpression(attribute.getNullableExpression())
								   .setValidationExpression(attribute.getValidationExpression())
								   .setEnumOptions(attribute.getEnumOptions())
								   .setRefEntity(getQuestionnaireReference(attribute.getRefEntity()))
								   .setCategoricalOptions(getCategoricalOptions(attribute))
								   .setAttributes(getAttributes(attribute))
								   .build();
	}

	private List<QuestionnaireChapter> getAttributes(Attribute attribute)
	{
		return stream(attribute.getChildren()).map(this::createQuestionnaireChapter).collect(toList());
	}

	private List<CategoricalOption> getCategoricalOptions(Attribute attribute)
	{
		EntityType refEntity = attribute.getRefEntity();
		if (refEntity == null)
		{
			return null;
		}

		return dataService.findAll(refEntity.getId())
						  .map(entity -> CategoricalOption.create(entity.getIdValue().toString(),
								  entity.getLabelValue().toString()))
						  .collect(Collectors.toList());
	}

	private QuestionnaireReference getQuestionnaireReference(EntityType refEntity)
	{
		if (refEntity == null)
		{
			return null;
		}

		return QuestionnaireReference.create(refEntity);
	}
}

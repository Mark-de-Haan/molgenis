package org.molgenis.questionnaires.service.impl;

import org.molgenis.data.DataService;
import org.molgenis.data.meta.MetaDataService;
import org.molgenis.data.meta.model.EntityType;
import org.molgenis.data.meta.model.EntityTypeFactory;
import org.molgenis.data.meta.model.EntityTypeMetadata;
import org.molgenis.questionnaires.meta.Questionnaire;
import org.molgenis.questionnaires.meta.QuestionnaireFactory;
import org.molgenis.questionnaires.meta.QuestionnaireMetaData;
import org.molgenis.questionnaires.request.QuestionnaireCreateRequest;
import org.molgenis.questionnaires.response.QuestionnaireResponse;
import org.molgenis.questionnaires.service.QuestionnaireService;
import org.molgenis.security.core.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.molgenis.data.meta.model.EntityTypeMetadata.ENTITY_TYPE_META_DATA;
import static org.molgenis.data.security.owned.OwnedEntityType.OWNER_USERNAME;
import static org.molgenis.data.support.QueryImpl.EQ;
import static org.molgenis.questionnaires.meta.QuestionnaireDataMetadata.QUESTIONNAIRE_DATA;
import static org.molgenis.questionnaires.meta.QuestionnaireMetaData.QUESTIONNAIRE;
import static org.molgenis.questionnaires.meta.QuestionnaireStatus.NOT_STARTED;
import static org.molgenis.questionnaires.meta.QuestionnaireStatus.OPEN;
import static org.molgenis.security.core.utils.SecurityUtils.getCurrentUsername;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService
{
	private static final String DEFAULT_SUBMISSION_TEXT = "<h3>Thank you for submitting the questionnaire.</h3>";

	private final DataService dataService;
	private final MetaDataService metadataService;
	private final QuestionnaireFactory questionnaireFactory;
	private final EntityTypeFactory entityTypeFactory;

	public QuestionnaireServiceImpl(DataService dataService, MetaDataService metadataService,
			EntityTypeFactory entityTypeFactory, QuestionnaireFactory questionnaireFactory)
	{
		this.dataService = requireNonNull(dataService);
		this.metadataService = requireNonNull(metadataService);
		this.questionnaireFactory = requireNonNull(questionnaireFactory);
		this.entityTypeFactory = requireNonNull(entityTypeFactory);
	}

	@Override
	public void createQuestionnaire(QuestionnaireCreateRequest request)
	{
		dataService.add(QuestionnaireMetaData.QUESTIONNAIRE, new Questionnaire(request));

		// Generate a new table that will contain the data for the newly created questionnaire
		EntityType questionnaireDataEntityType = metadataService.getEntityType(QUESTIONNAIRE_DATA)
		EntityType questionnaireDataTable = entityTypeFactory.create(request.getName());
		questionnaireDataTable.setExtends(questionnaireDataEntityType);

		metadataService.addEntityType(questionnaireDataTable);
	}

	@Override
	public List<QuestionnaireResponse> getQuestionnaires()
	{
		
	}

	@Override
	public QuestionnaireResponse getQuestionnaire(String id)
	{
		Questionnaire questionnaire = findQuestionnaireEntity(id);
		if (questionnaire.getStatus().equals(NOT_STARTED))
		{
			// Set questionnaire status to open once it has been requested
			questionnaire.setStatus(OPEN);
			dataService.update(id, questionnaire);
		}
		return QuestionnaireResponse.create(questionnaire);
	}

	private Questionnaire findQuestionnaireEntity(String entityTypeId)
	{
		return questionnaireFactory.create(dataService.findOne(entityTypeId, EQ(OWNER_USERNAME, getCurrentUsername())));
	}
}

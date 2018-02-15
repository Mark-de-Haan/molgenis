package org.molgenis.questionnaires.meta;

import org.molgenis.data.meta.SystemEntityType;
import org.molgenis.data.security.owned.OwnedEntityType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.molgenis.data.meta.AttributeType.ENUM;
import static org.molgenis.data.meta.AttributeType.TEXT;
import static org.molgenis.data.meta.model.EntityType.AttributeRole.ROLE_ID;
import static org.molgenis.data.meta.model.Package.PACKAGE_SEPARATOR;
import static org.molgenis.data.system.model.RootSystemPackage.PACKAGE_SYSTEM;

@Component
public class QuestionnaireDataMetadata extends SystemEntityType
{
	private static final String SIMPLE_NAME = "QuestionnaireData";
	public static final String QUESTIONNAIRE_DATA = PACKAGE_SYSTEM + PACKAGE_SEPARATOR + SIMPLE_NAME;

	public static final String ID = "id";
	public static final String DATA = "data";
	public static final String STATUS = "status";

	private final OwnedEntityType ownedEntityType;

	QuestionnaireDataMetadata(OwnedEntityType ownedEntityType)
	{
		super(SIMPLE_NAME, PACKAGE_SYSTEM);
		this.ownedEntityType = requireNonNull(ownedEntityType);
	}

	@Override
	public void init()
	{
		setLabel("QuestionnaireData");
		setAbstract(true);
		setExtends(ownedEntityType);

		addAttribute(ID, ROLE_ID).setAuto(true);
		addAttribute(DATA).setDataType(TEXT).setNillable(true);

		List<String> enumOptions = new ArrayList<>();
		for (QuestionnaireStatus questionnaireStatus : QuestionnaireStatus.values())
		{
			enumOptions.add(questionnaireStatus.toString());
		}

		addAttribute(STATUS).setDataType(ENUM).setEnumOptions(enumOptions).setVisible(false).setNillable(false);
	}
}

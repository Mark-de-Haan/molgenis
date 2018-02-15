package org.molgenis.questionnaires.meta;

import org.molgenis.data.meta.SystemEntityType;
import org.springframework.stereotype.Component;

import static org.molgenis.data.meta.AttributeType.SCRIPT;
import static org.molgenis.data.meta.model.EntityType.AttributeRole.ROLE_ID;
import static org.molgenis.data.meta.model.Package.PACKAGE_SEPARATOR;
import static org.molgenis.data.system.model.RootSystemPackage.PACKAGE_SYSTEM;

/**
 * Base EntityType for 'questionnaire' entities
 */
@Component
public class QuestionnaireMetaData extends SystemEntityType
{
	private static final String SIMPLE_NAME = "Questionnaire";
	public static final String QUESTIONNAIRE = PACKAGE_SYSTEM + PACKAGE_SEPARATOR + SIMPLE_NAME;

	public static final String NAME = "name";
	public static final String SCHEMA = "schema";

	QuestionnaireMetaData()
	{
		super(SIMPLE_NAME, PACKAGE_SYSTEM);
	}

	@Override
	public void init()
	{
		setLabel("Questionnaire");
		setDescription("A table containing questionnaires");

		addAttribute(NAME, ROLE_ID);
		addAttribute(SCHEMA).setDataType(SCRIPT)
							.setNillable(false)
							.setLabel("Questionnaire Schema")
							.setDescription("JSON Schema to generate a SurveyJS questionnaire");
	}
}

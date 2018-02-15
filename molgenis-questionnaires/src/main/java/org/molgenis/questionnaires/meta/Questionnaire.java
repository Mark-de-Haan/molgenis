package org.molgenis.questionnaires.meta;

import org.molgenis.data.Entity;
import org.molgenis.data.meta.model.EntityType;
import org.molgenis.data.support.StaticEntity;
import org.molgenis.questionnaires.request.QuestionnaireCreateRequest;

import static org.molgenis.questionnaires.meta.QuestionnaireMetaData.NAME;
import static org.molgenis.questionnaires.meta.QuestionnaireMetaData.SCHEMA;

public class Questionnaire extends StaticEntity
{
	public Questionnaire(Entity entity)
	{
		super(entity);
	}

	public Questionnaire(EntityType entityType)
	{
		super(entityType);
	}

	public Questionnaire(QuestionnaireCreateRequest request)
	{
		setName(request.getName());
		setSchema(request.getSchema());
	}

	public String getName()
	{
		return getString(NAME);
	}

	public void setName(String name)
	{
		set(NAME, name);
	}

	public String getSchema()
	{
		return getString(SCHEMA);
	}

	public void setSchema(String schema)
	{
		set(SCHEMA, schema);
	}
}

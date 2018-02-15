package org.molgenis.questionnaires.request;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireCreateRequest.class)
public abstract class QuestionnaireCreateRequest
{
	public abstract String getName();

	public abstract String getSchema();
}

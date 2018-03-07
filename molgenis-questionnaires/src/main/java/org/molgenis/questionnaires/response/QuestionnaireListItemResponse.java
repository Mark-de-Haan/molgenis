package org.molgenis.questionnaires.response;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;
import org.molgenis.questionnaires.meta.QuestionnaireStatus;

import javax.annotation.Nullable;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireListItemResponse.class)
public abstract class QuestionnaireListItemResponse
{
	public abstract String getId();

	public abstract String getLabel();

	@Nullable
	public abstract String getDescription();

	public abstract QuestionnaireStatus getStatus();

	public static QuestionnaireListItemResponse create(String id, String label, String description, QuestionnaireStatus status)
	{
		return new AutoValue_QuestionnaireListItemResponse(id, label, description, status);
	}
}

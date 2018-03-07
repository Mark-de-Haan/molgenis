package org.molgenis.questionnaires.response;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;
import org.molgenis.questionnaires.meta.Questionnaire;
import org.molgenis.questionnaires.model.QuestionnaireMeta;

import javax.annotation.Nullable;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireResponse.class)
public abstract class QuestionnaireResponse
{
	public abstract String getRowId();

	public abstract String getLabel();

	@Nullable
	public abstract String getDescription();

	public abstract QuestionnaireMeta getMeta();

	public abstract Questionnaire getData();

	public static QuestionnaireResponse create(String rowId, String label, String description,
			QuestionnaireMeta metadata, Questionnaire data)
	{
		return new AutoValue_QuestionnaireResponse(rowId, label, description, metadata, data);
	}
}

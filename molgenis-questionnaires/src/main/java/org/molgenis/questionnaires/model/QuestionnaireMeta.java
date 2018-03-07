package org.molgenis.questionnaires.model;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

import java.util.List;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireMeta.class)
public abstract class QuestionnaireMeta
{
	public abstract List<QuestionnaireChapter> getAttributes();

	public static QuestionnaireMeta create(List<QuestionnaireChapter> attributes)
	{
		return new AutoValue_QuestionnaireMeta(attributes);
	}
}

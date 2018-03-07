package org.molgenis.questionnaires.model;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;
import org.molgenis.data.meta.model.EntityType;

import javax.annotation.Nullable;

@AutoValue
@AutoGson(autoValueClass = AutoValue_QuestionnaireReference.class)
public abstract class QuestionnaireReference
{
	public abstract String getIdAttribute();

	@Nullable
	public abstract String getLabelAttribute();

	public static QuestionnaireReference create(EntityType refEntity)
	{
		String idAttribute = refEntity.getIdAttribute().getName();
		String labelAttribute = refEntity.getLabelAttribute().getName();

		return new AutoValue_QuestionnaireReference(idAttribute, labelAttribute);
	}
}

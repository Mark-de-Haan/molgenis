package org.molgenis.questionnaires.model;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_CategoricalOption.class)
public abstract class CategoricalOption
{
	public abstract String getId();

	public abstract String getLabel();

	public static CategoricalOption create(String id, String label)
	{
		return new AutoValue_CategoricalOption(id, label);
	}
}

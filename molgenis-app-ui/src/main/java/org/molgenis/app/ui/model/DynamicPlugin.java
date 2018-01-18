package org.molgenis.app.ui.model;

import com.google.auto.value.AutoValue;
import org.molgenis.gson.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_DynamicPlugin.class)
public abstract class DynamicPlugin
{
	public abstract String getId();

	public abstract String getLabel();

	public abstract Boolean getActive();

	public static DynamicPlugin create(String id, String label, Boolean active)
	{
		return new AutoValue_DynamicPlugin(id, label, active);
	}
}

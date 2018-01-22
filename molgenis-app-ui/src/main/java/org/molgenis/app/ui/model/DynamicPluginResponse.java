package org.molgenis.app.ui.model;

import com.google.auto.value.AutoValue;
import org.molgenis.core.gson.AutoGson;

@AutoValue
@AutoGson(autoValueClass = AutoValue_DynamicPluginResponse.class)
public abstract class DynamicPluginResponse
{
	public abstract String getId();

	public abstract String getLabel();

	public abstract Boolean getActive();

	public abstract String getResourceLocation();

	public static DynamicPluginResponse create(String id, String label, Boolean active, String resourceLocation)
	{
		return new AutoValue_DynamicPluginResponse(id, label, active, resourceLocation);
	}
}

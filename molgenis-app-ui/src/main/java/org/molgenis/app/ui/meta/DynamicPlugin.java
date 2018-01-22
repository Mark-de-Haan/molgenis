package org.molgenis.app.ui.meta;

import org.molgenis.app.ui.model.DynamicPluginResponse;
import org.molgenis.data.meta.model.EntityType;
import org.molgenis.data.support.StaticEntity;

public class DynamicPlugin extends StaticEntity
{
	public DynamicPlugin(EntityType entityType)
	{
		super(entityType);
	}

	/**
	 * Generate a GSON representation of the {@link DynamicPlugin} object
	 */
	public DynamicPluginResponse toDynamicPluginResponse()
	{
		return DynamicPluginResponse.create(getId(), getName(), isActive(), getResourceLocation());
	}

	public String getId()
	{
		return getString(DynamicPluginMetadata.ID);
	}

	public String getName()
	{
		return getString(DynamicPluginMetadata.NAME);
	}

	public Boolean isActive()
	{
		return getBoolean(DynamicPluginMetadata.ACTIVE);
	}

	public String getResourceLocation()
	{
		return getString(DynamicPluginMetadata.RESOURCE_LOCATION);
	}

	public void setActive(boolean active)
	{
		set(DynamicPluginMetadata.ACTIVE, active);
	}
}

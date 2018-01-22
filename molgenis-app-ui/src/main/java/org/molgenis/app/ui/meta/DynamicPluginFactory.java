package org.molgenis.app.ui.meta;

import org.molgenis.data.AbstractSystemEntityFactory;
import org.molgenis.data.populate.EntityPopulator;
import org.springframework.stereotype.Component;

@Component
public class DynamicPluginFactory extends AbstractSystemEntityFactory<DynamicPlugin, DynamicPluginMetadata, String>
{
	DynamicPluginFactory(DynamicPluginMetadata dynamicPluginMetadata, EntityPopulator entityPopulator)
	{
		super(DynamicPlugin.class, dynamicPluginMetadata, entityPopulator);
	}
}

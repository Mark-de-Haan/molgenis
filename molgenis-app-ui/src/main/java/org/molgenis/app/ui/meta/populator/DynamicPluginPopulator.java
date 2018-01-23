package org.molgenis.app.ui.meta.populator;

import org.molgenis.app.ui.meta.DynamicPlugin;
import org.molgenis.data.DataService;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.molgenis.app.ui.meta.DynamicPluginMetadata.DYNAMIC_PLUGIN;

//@Component
public class DynamicPluginPopulator
{
	private final DataService dataService;

	DynamicPluginPopulator(DataService dataService)
	{
		this.dataService = requireNonNull(dataService);
	}

	/**
	 * Populate DynamicPlugin repository with plugins registered in the dynamic-plugin.yaml
	 */
	public void populate()
	{

	}

	private void persist(List<DynamicPlugin> dynamicPlugins)
	{
		dataService.add(DYNAMIC_PLUGIN, dynamicPlugins.stream());
	}
}

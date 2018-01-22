package org.molgenis.app.ui.service.impl;

import org.molgenis.app.ui.meta.DynamicPlugin;
import org.molgenis.app.ui.model.DynamicPluginResponse;
import org.molgenis.app.ui.service.DynamicPluginService;
import org.molgenis.data.DataService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.molgenis.app.ui.meta.DynamicPluginMetadata.DYNAMIC_PLUGIN;

@Component
public class DynamicPluginServiceImpl implements DynamicPluginService
{
	private DataService dataService;

	public DynamicPluginServiceImpl(DataService dataService)
	{
		this.dataService = requireNonNull(dataService);
	}

	@Override
	public List<DynamicPluginResponse> getDynamicPlugins()
	{
		return dataService.findAll(DYNAMIC_PLUGIN, DynamicPlugin.class)
						  .map(DynamicPlugin::toDynamicPluginResponse)
						  .collect(toList());
	}

	@Override
	public void activatePlugin(String id)
	{
		DynamicPlugin dynamicPlugin = dataService.findOneById(DYNAMIC_PLUGIN, id, DynamicPlugin.class);
		dynamicPlugin.setActive(true);
		dataService.update(DYNAMIC_PLUGIN, dynamicPlugin);
	}

	@Override
	public void deactivatePlugin(String id)
	{
		DynamicPlugin dynamicPlugin = dataService.findOneById(DYNAMIC_PLUGIN, id, DynamicPlugin.class);
		dynamicPlugin.setActive(false);
		dataService.update(DYNAMIC_PLUGIN, dynamicPlugin);
	}
}

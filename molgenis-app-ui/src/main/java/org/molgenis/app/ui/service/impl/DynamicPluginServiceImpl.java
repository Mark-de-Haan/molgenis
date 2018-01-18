package org.molgenis.app.ui.service.impl;

import org.molgenis.app.ui.model.DynamicPlugin;
import org.molgenis.app.ui.service.DynamicPluginService;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class DynamicPluginServiceImpl implements DynamicPluginService
{
	@Override
	public List<DynamicPlugin> getDynamicPlugins()
	{
		DynamicPlugin dynamicPlugin = DynamicPlugin.create("plugin_id_test", "Test plugin", false);
		return newArrayList(dynamicPlugin);
	}

	@Override
	public void activatePlugin(String id)
	{
		System.out.println("id = " + id);
	}
}

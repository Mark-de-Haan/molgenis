package org.molgenis.app.ui.service;

import org.molgenis.app.ui.model.DynamicPlugin;

import java.util.List;

public interface DynamicPluginService
{
	List<DynamicPlugin> getDynamicPlugins();

	void activatePlugin (String id);
}

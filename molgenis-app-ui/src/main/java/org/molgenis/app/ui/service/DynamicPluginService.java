package org.molgenis.app.ui.service;

import org.molgenis.app.ui.model.DynamicPluginResponse;

import java.util.List;

public interface DynamicPluginService
{
	List<DynamicPluginResponse> getDynamicPlugins();

	void activatePlugin (String id);

	void deactivatePlugin (String id);
}

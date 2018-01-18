package org.molgenis.app.ui.controller;

import org.molgenis.app.ui.model.DynamicPlugin;
import org.molgenis.app.ui.service.DynamicPluginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.molgenis.app.ui.controller.DynamicPluginController.DYNAMIC_PLUGIN_CONTROLLER_URI;

@Controller
@RequestMapping(DYNAMIC_PLUGIN_CONTROLLER_URI)
public class DynamicPluginController
{
	private static final Logger LOG = LoggerFactory.getLogger(DynamicPluginController.class);

	static final String DYNAMIC_PLUGIN_CONTROLLER_URI = "/plugins";

	private DynamicPluginService dynamicPluginService;

	public DynamicPluginController(DynamicPluginService dynamicPluginService)
	{
		this.dynamicPluginService = requireNonNull(dynamicPluginService);
	}

	@GetMapping("/list")
	@ResponseBody
	public List<DynamicPlugin> getDynamicPlugins()
	{
		return dynamicPluginService.getDynamicPlugins();
	}

	@PostMapping("/activate/{id}")
	public void activatePlugin(@PathVariable(value = "id") String id)
	{
		dynamicPluginService.activatePlugin(id);
	}
}

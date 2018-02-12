package org.molgenis.sass.controller;

import org.molgenis.web.PluginController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SassController.URI)
public class SassController extends PluginController
{
	private static final String ID = "sass";
	public static final String URI = PluginController.PLUGIN_URI_PREFIX + ID;

	public SassController()
	{
		super(URI);
	}

	@GetMapping
	public String init(Model model)
	{
		return "view-sass";
	}
}

package org.molgenis.app.ui.controller;

import org.molgenis.app.ui.service.ApplicationInitializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.Objects.requireNonNull;
import static org.molgenis.app.ui.controller.ApplicationController.APPLICATION_ROOT_URI;

@Controller
@RequestMapping(APPLICATION_ROOT_URI)
public class ApplicationController
{
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

	static final String APPLICATION_ROOT_URI = "/";
	private static final String APPLICATION_ROOT_VIEW = "view-application";

	private ApplicationInitializationService applicationInitializationService;

	public ApplicationController(ApplicationInitializationService applicationInitializationService)
	{
		this.applicationInitializationService = requireNonNull(applicationInitializationService);
	}

	@GetMapping("/**")
	public String initApplication(Model model)
	{
		LOG.debug("Loading MOLGENIS application");

		LOG.debug("Loading initial model through application initialization service");
		applicationInitializationService.loadInitialModel(model);

		LOG.debug("Loading application view");
		return APPLICATION_ROOT_VIEW;
	}
}

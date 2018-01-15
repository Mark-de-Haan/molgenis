package org.molgenis.app.ui.service;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ApplicationInitializationServiceImpl implements ApplicationInitializationService
{
	@Override
	public void loadInitialModel(Model model)
	{
		addApplicationTitleToModel(model);
	}

	private void addApplicationTitleToModel(Model model)
	{
		model.addAttribute("application_title", "MOLGENIS");
	}
}

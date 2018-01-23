package org.molgenis.core.ui;

/**
 * Redirects '/' to the active plugin in the main menu
 */
//@Controller
//@RequestMapping("/") Disable root listener due to SPA project
public class MolgenisRootController
{
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String index()
	{
		return "forward:" + MolgenisMenuController.URI;
	}
}

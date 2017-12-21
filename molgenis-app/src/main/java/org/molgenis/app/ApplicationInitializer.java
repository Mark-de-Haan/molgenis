package org.molgenis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@ComponentScan
@EnableWebMvc
public class ApplicationInitializer extends SpringBootServletInitializer
{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		super.onStartup(servletContext, WebAppConfig.class, 150);
	}

	@Bean
	@Autowired
	DispatcherServlet getDispatchServlet(WebApplicationContext webAppContext)
	{
		return new DispatcherServlet(webAppContext);
	}
}


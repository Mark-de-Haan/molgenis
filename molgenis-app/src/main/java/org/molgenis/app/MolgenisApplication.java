package org.molgenis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class MolgenisApplication extends SpringBootServletInitializer
{
	private static final int SERVER_PORT = 8080;

	@Bean
	@Autowired
	DispatcherServlet getDispatchServlet(WebApplicationContext webAppContext)
	{
		return new DispatcherServlet(webAppContext);
	}

	@Bean
	EmbeddedServletContainerFactory getTomcatEmbeddedServletContainerFactory()
	{
		return new TomcatEmbeddedServletContainerFactory(SERVER_PORT);
	}
}


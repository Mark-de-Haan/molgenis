package org.molgenis.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
// Use the following to use dynamically configured properties
//@PropertySource({
//		"classpath:persistence-${envTarget:mysql}.properties"
//})
public class ApplicationProperties
{
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}

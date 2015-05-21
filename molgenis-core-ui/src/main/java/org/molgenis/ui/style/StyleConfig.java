package org.molgenis.ui.style;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class StyleConfig
{
	@Bean
	public StyleService styleService()
	{
		// ClientHttpRequestFactory with low timeout so that we can quickly check if the stylesheets are available
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(400);
		factory.setReadTimeout(1000);
		return new StyleServiceImpl(factory);
	}
}

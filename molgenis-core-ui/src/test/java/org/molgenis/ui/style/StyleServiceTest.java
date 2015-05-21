package org.molgenis.ui.style;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.molgenis.ui.style.StyleServiceImpl.BOOTSWATCH_API_URL;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.molgenis.framework.server.MolgenisSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

@ContextConfiguration(classes = StyleServiceTest.Config.class)
public class StyleServiceTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private MolgenisSettings molgenisSettings;

	@Autowired
	private StyleService styleService;

	@Autowired
	private ClientHttpRequestFactory requestFactory;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testGetAvailableStylesOffline() throws IOException, URISyntaxException
	{
		ClientHttpRequest httpRequestMock = mock(ClientHttpRequest.class);
		when(requestFactory.createRequest(new URI(StyleServiceImpl.BOOTSWATCH_API_URL), HttpMethod.HEAD)).thenReturn(
				httpRequestMock);
		when(httpRequestMock.execute()).thenThrow(new IOException("Timeout"));
		assertEquals(styleService.getAvailableStyles(), Arrays.asList(Style.createLocal("bootstrap-basic.min.css"),
				Style.createLocal("bootstrap-ibd.min.css"), Style.createLocal("bootstrap-molgenis.min.css")));
	}

	@Test
	public void testGetAvailableStylesOnline() throws IOException, URISyntaxException
	{
		ClientHttpRequest httpRequestMock = mock(ClientHttpRequest.class);
		when(requestFactory.createRequest(new URI(BOOTSWATCH_API_URL), HttpMethod.HEAD)).thenReturn(httpRequestMock);
		ClientHttpResponse responseMock = mock(ClientHttpResponse.class);
		when(httpRequestMock.execute()).thenReturn(responseMock);
		when(responseMock.getStatusCode()).thenReturn(HttpStatus.OK);

		when(restTemplate.getForObject(BOOTSWATCH_API_URL, String.class, ""))
				.thenReturn(
						"{'version':'3.3.4',"
								+ "'themes':["
								+ "{'name':'Cerulean','description':'A calm blue sky','thumbnail':'http://bootswatch.com/cerulean/thumbnail.png',"
								+ "'preview':'http://bootswatch.com/cerulean/','css':'http://bootswatch.com/cerulean/bootstrap.css',"
								+ "'cssMin':'http://bootswatch.com/cerulean/bootstrap.min.css','cssCdn':'//netdna.bootstrapcdn.com/bootswatch/latest/cerulean/bootstrap.min.css',"
								+ "'less':'http://bootswatch.com/cerulean/bootswatch.less','lessVariables':'http://bootswatch.com/cerulean/variables.less',"
								+ "'scss':'http://bootswatch.com/cerulean/_bootswatch.scss','scssVariables':'http://bootswatch.com/cerulean/_variables.scss'},"
								+ "{'name':'Cosmo','description':'An ode to Metro','thumbnail':'http://bootswatch.com/cosmo/thumbnail.png',"
								+ "'preview':'http://bootswatch.com/cosmo/','css':'http://bootswatch.com/cosmo/bootstrap.css',"
								+ "'cssMin':'http://bootswatch.com/cosmo/bootstrap.min.css','cssCdn':'//netdna.bootstrapcdn.com/bootswatch/latest/cosmo/bootstrap.min.css',"
								+ "'less':'http://bootswatch.com/cosmo/bootswatch.less','lessVariables':'http://bootswatch.com/cosmo/variables.less',"
								+ "ß'scss':'http://bootswatch.com/cosmo/_bootswatch.scss','scssVariables':'http://bootswatch.com/cosmo/_variables.scss'}]}");
		assertEquals(
				styleService.getAvailableStyles(),
				Arrays.asList(Style.createRemote("//bootswatch.com/cerulean/bootstrap.min.css", "Cerulean"),
						Style.createRemote("//bootswatch.com/cosmo/bootstrap.min.css", "Cosmo"),
						Style.createLocal("bootstrap-basic.min.css"), Style.createLocal("bootstrap-ibd.min.css"),
						Style.createLocal("bootstrap-molgenis.min.css")));
	}

	@Test
	public void testSetSelectedStyle()
	{
	}

	@Test
	public void testGetSelectedStyle()
	{

	}

	@Configuration
	static class Config
	{
		@Bean
		MolgenisSettings molgenisSettings()
		{
			return mock(MolgenisSettings.class);
		}

		@Bean
		StyleService styleService()
		{
			return new StyleServiceImpl(requestFactory());
		}

		@Bean
		ClientHttpRequestFactory requestFactory()
		{
			return mock(ClientHttpRequestFactory.class);
		}

		@Bean
		RestTemplate template()
		{
			return mock(RestTemplate.class);
		}
	}
}

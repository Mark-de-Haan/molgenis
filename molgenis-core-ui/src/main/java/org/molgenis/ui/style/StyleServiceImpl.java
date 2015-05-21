package org.molgenis.ui.style;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.molgenis.framework.server.MolgenisSettings;
import org.molgenis.ui.MolgenisPluginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class StyleServiceImpl implements StyleService
{
	private static final String LOCAL_CSS_BOOTSTRAP_THEME_LOCATION = "css/themes/bootstrap-*.css";
	private static final String THEME_NAME_KEY = "name";
	private static final String CSS_MIN_KEY = "cssMin";
	private static final String THEMES_KEY = "themes";
	static final String BOOTSWATCH_API_URL = "http://api.bootswatch.com/3/";
	private static final String CSS_THEME_KEY = MolgenisPluginInterceptor.MOLGENIS_CSS_THEME;

	private static final Logger LOG = LoggerFactory.getLogger(StyleServiceImpl.class);

	@Autowired
	private MolgenisSettings molgenisSettings;

	@Autowired
	private RestTemplate restTemplate;

	private ClientHttpRequestFactory clientHttpRequestFactory;

	public StyleServiceImpl(ClientHttpRequestFactory clientHttpRequestFactory)
	{
		this.clientHttpRequestFactory = clientHttpRequestFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Style> getAvailableStyles()
	{
		Map<String, Style> availableStyles = new TreeMap<String, Style>();

		if (isURLReachable(BOOTSWATCH_API_URL))
		{
			String jsonString = restTemplate.getForObject(BOOTSWATCH_API_URL, String.class, "");

			Gson gson = new Gson();
			Map<String, List<Map<String, String>>> bootSwatchApiResponse = gson.fromJson(jsonString, Map.class);

			List<Map<String, String>> themes = bootSwatchApiResponse.get(THEMES_KEY);

			// Replace 'http://' with '//' to allow the parent page to set the protocol to either https or http
			themes.forEach(tree -> {
				Style style = Style.createRemote(tree.get(CSS_MIN_KEY).replace("http:", ""), tree.get(THEME_NAME_KEY));
				availableStyles.put(style.getName(), style);
			});
		}

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try
		{
			Resource[] resources = resolver.getResources(LOCAL_CSS_BOOTSTRAP_THEME_LOCATION);
			for (Resource resource : resources)
			{
				Style style = Style.createLocal(resource.getFilename());
				if (!availableStyles.containsKey(style.getName()) || style.getLocation().contains(".min.css"))
				{
					availableStyles.put(style.getName(), style);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return availableStyles.values();
	}

	@Override
	public void setSelectedStyle(String styleName)
	{
		molgenisSettings.setProperty(CSS_THEME_KEY, getStyle(styleName).getLocation());
	}

	@Override
	public Style getSelectedStyle()
	{
		for (Style style : getAvailableStyles())
		{
			if (style.getLocation().equals(molgenisSettings.getProperty(CSS_THEME_KEY)))
			{
				return style;
			}
		}
		return null;
	}

	@Override
	public Style getStyle(String styleName)
	{
		try
		{
			for (Style style : getAvailableStyles())
			{
				if (style.getName().equals(styleName))
				{
					return style;
				}
			}
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e + " Selected style was not found");
		}

		return null;
	}

	/**
	 * Pings a HTTP URL. This effectively sends a HEAD request and returns <code>true</code> if the response code is in
	 * the 200-399 range.
	 * 
	 * @param url
	 *            The HTTP URL to be pinged.
	 * @param timeout
	 *            The timeout in millis for both the connection timeout and the response read timeout. Note that the
	 *            total timeout is effectively two times the given timeout.
	 * @return <code>true</code> if the given HTTP URL has returned response code 200-399 on a HEAD request within the
	 *         given timeout, otherwise <code>false</code>.
	 */
	private boolean isURLReachable(String url)
	{
		// Otherwise an exception may be thrown on invalid SSL certificates:
		url = url.replaceFirst("^https", "http");

		try
		{
			ClientHttpRequest request = clientHttpRequestFactory.createRequest(new URI(url), HttpMethod.HEAD);
			ClientHttpResponse response = request.execute();
			HttpStatus status = response.getStatusCode();
			return status.is2xxSuccessful();
		}
		catch (IOException exception)
		{
			LOG.warn("Failed to connect to bootswatch server, serving only local styles");
			return false;
		}
		catch (URISyntaxException e)
		{
			LOG.error("Error parsing bootswatch style URL!", e);
			return false;
		}
	}
}

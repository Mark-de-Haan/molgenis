package org.molgenis.dataexplorer.negotiator.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum NegotiatorAuthenticationType
{
	DIGEST, BEARER;

	/**
	 * Returns the value string for the given enum value
	 *
	 * @param value enum value
	 * @return value string
	 */
	public static String getValueString(NegotiatorAuthenticationType value)
	{
		return value.toString();
	}

	/**
	 * Returns the value strings for all enum types in the defined enum order
	 *
	 * @return value strings
	 */
	public static List<String> getOptions()
	{
		return Stream.of(values()).map(NegotiatorAuthenticationType::getValueString).collect(Collectors.toList());
	}
}

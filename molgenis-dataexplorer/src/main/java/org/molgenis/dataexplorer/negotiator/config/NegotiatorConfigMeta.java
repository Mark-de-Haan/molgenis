package org.molgenis.dataexplorer.negotiator.config;

import org.molgenis.data.meta.SystemEntityType;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;
import static org.molgenis.data.meta.AttributeType.ENUM;
import static org.molgenis.data.meta.AttributeType.STRING;
import static org.molgenis.data.meta.model.EntityType.AttributeRole.ROLE_ID;
import static org.molgenis.dataexplorer.negotiator.config.NegotiatorAuthenticationType.*;

@Component
class NegotiatorConfigMeta extends SystemEntityType
{
	private static final String SIMPLE_NAME = "NegotiatorConfig";

	private static final String ID = "id";

	public static final String NEGOTIATOR_URL = "negotiator_url";
	public static final String USERNAME = "username";
	@java.lang.SuppressWarnings("squid:S2068")
	public static final String PASSWORD = "password";
	public static final String AUTHENTICATION = "authentication";
	public static final String BEARER_TOKEN_URL = "bearer_token_url";
	public static final String BEARER_TOKEN_USERNAME = "bearer_token_username";
	@java.lang.SuppressWarnings("squid:S2068")
	public static final String BEARER_TOKEN_PASSWORD = "bearer_token_password";

	private static final String BEARER_TOKEN_URL_NULLABLE_EXPRESSION =
			"$('" + AUTHENTICATION + "').value() !== '" + getValueString(BEARER) + "'";

	private static final String BEARER_TOKEN_URL_VISIBLE_EXPRESSION =
			"$('" + AUTHENTICATION + "').value() === '" + getValueString(BEARER) + "'";

	private final NegotiatorPackage negotiatorPackage;

	public NegotiatorConfigMeta(NegotiatorPackage negotiatorPackage)
	{
		super(SIMPLE_NAME, NegotiatorPackage.PACKAGE_NEGOTIATOR);
		this.negotiatorPackage = requireNonNull(negotiatorPackage);
	}

	@Override
	protected void init()
	{
		setLabel("Negotiator Config");
		setPackage(negotiatorPackage);

		addAttribute(ID, ROLE_ID).setDataType(STRING).setNillable(false).setLabel("Identifier for this config");
		addAttribute(NEGOTIATOR_URL).setLabel("Negotiator URL").setNillable(false);
		addAttribute(USERNAME).setDataType(STRING).setNillable(false).setLabel("Username for the negotiator");
		addAttribute(PASSWORD).setDataType(STRING).setNillable(false).setLabel("Password for the negotiator");

		addAttribute(AUTHENTICATION).setDataType(ENUM)
									.setEnumOptions(getOptions())
									.setDefaultValue(getValueString(DIGEST))
									.setLabel("Authentication type")
									.setDescription(
											"Authentication type for third party API. Defaults to base64 encoded digest authentication.");

		addAttribute(BEARER_TOKEN_URL).setVisibleExpression(BEARER_TOKEN_URL_VISIBLE_EXPRESSION)
									  .setNullableExpression(BEARER_TOKEN_URL_NULLABLE_EXPRESSION)
									  .setDescription(
											  "Defines the endpoint from which a Bearer token needs to be fetched.")
									  .setLabel("Bearer token endpoint url");

		addAttribute(BEARER_TOKEN_USERNAME).setVisibleExpression(BEARER_TOKEN_URL_VISIBLE_EXPRESSION)
										   .setNullableExpression(BEARER_TOKEN_URL_NULLABLE_EXPRESSION)
										   .setDescription("Set username used for Bearer endpoint authentication.")
										   .setLabel("Bearer token endpoint username");

		addAttribute(BEARER_TOKEN_PASSWORD).setVisibleExpression(BEARER_TOKEN_URL_VISIBLE_EXPRESSION)
										   .setNullableExpression(BEARER_TOKEN_URL_NULLABLE_EXPRESSION)
										   .setDescription("Set password used for Bearer endpoint authentication.")
										   .setLabel("Bearer token endpoint password");
	}
}


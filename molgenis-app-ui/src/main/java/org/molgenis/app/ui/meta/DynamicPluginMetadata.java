package org.molgenis.app.ui.meta;

import org.molgenis.data.meta.SystemEntityType;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;
import static org.molgenis.app.ui.meta.UserInterfacePackage.USER_INTERFACE_PACKAGE;
import static org.molgenis.data.meta.AttributeType.BOOL;
import static org.molgenis.data.meta.AttributeType.TEXT;
import static org.molgenis.data.meta.model.EntityType.AttributeRole.*;
import static org.molgenis.data.meta.model.Package.PACKAGE_SEPARATOR;

@Component
public class DynamicPluginMetadata extends SystemEntityType
{
	private static final String SIMPLE_NAME = "DynamicPlugin";
	public static final String DYNAMIC_PLUGIN = USER_INTERFACE_PACKAGE + PACKAGE_SEPARATOR + SIMPLE_NAME;

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String RESOURCE_LOCATION = "resourceLocation";

	private final UserInterfacePackage userInterfacePackage;

	DynamicPluginMetadata(UserInterfacePackage userInterfacePackage)
	{
		super(SIMPLE_NAME, USER_INTERFACE_PACKAGE);
		this.userInterfacePackage = requireNonNull(userInterfacePackage);
	}

	@Override
	public void init()
	{
		setLabel("Dynamic Plugin");
		setDescription("Dynamic Plugin repository containing resources that provide a user interface.");
		setPackage(userInterfacePackage);

		addAttribute(ID, ROLE_ID).setNillable(false).setAuto(true).setVisible(false);
		addAttribute(NAME, ROLE_LABEL, ROLE_LOOKUP).setNillable(false).setUnique(true);
		addAttribute(ACTIVE).setDefaultValue(String.valueOf(false)).setDataType(BOOL);
		addAttribute(RESOURCE_LOCATION).setNillable(false).setDataType(TEXT);
	}
}

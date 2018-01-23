package org.molgenis.app.ui.meta;

import org.molgenis.data.meta.SystemPackage;
import org.molgenis.data.meta.model.PackageMetadata;
import org.molgenis.data.system.model.RootSystemPackage;

import static java.util.Objects.requireNonNull;
import static org.molgenis.data.system.model.RootSystemPackage.PACKAGE_SYSTEM;

//@Component
public class UserInterfacePackage extends SystemPackage
{
	public static final String SIMPLE_NAME = "ui";
	public static final String USER_INTERFACE_PACKAGE = PACKAGE_SYSTEM + PACKAGE_SEPARATOR + SIMPLE_NAME;

	private final RootSystemPackage rootSystemPackage;

	public UserInterfacePackage(PackageMetadata packageMetadata, RootSystemPackage rootSystemPackage)
	{
		super(USER_INTERFACE_PACKAGE, packageMetadata);
		this.rootSystemPackage = requireNonNull(rootSystemPackage);
	}

	@Override
	protected void init()
	{
		setLabel("User Interface");
		setDescription("A package containing all data repositories pertaining to the user interface");
		setParent(rootSystemPackage);
	}
}

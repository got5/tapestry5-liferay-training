package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
	public static void bind(ServiceBinder binder)
    {
		binder.bind(ProductCatalog.class, ProductCatalogImpl.class);
    }
	
    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
    }
}

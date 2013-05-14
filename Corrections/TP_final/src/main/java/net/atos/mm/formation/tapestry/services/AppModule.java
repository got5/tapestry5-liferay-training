package net.atos.mm.formation.tapestry.services;

import net.atos.mm.formation.tapestry.model.UserBasket;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.portlet.PortletPersistenceConstants;
import org.apache.tapestry5.services.ApplicationStateContribution;
import org.apache.tapestry5.services.ApplicationStateCreator;

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
    
    /**
     * Define new persistence strategy for UserBasket object (APPLICATION_SCOPE).
     */
    public void contributeApplicationStateManager(MappedConfiguration<Class, ApplicationStateContribution> configuration)
    {
      ApplicationStateCreator<UserBasket> creator = new ApplicationStateCreator<UserBasket>()
      {
        public UserBasket create()
        {
          return new UserBasket();
        }
      };
      
      configuration.add(UserBasket.class, new ApplicationStateContribution(PortletPersistenceConstants.PORTLET_SESSION_APPLICATION_SCOPE, creator));
    }
}

package net.atos.mm.formation.tapestry.pages;

import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

import net.atos.mm.formation.tapestry.data.Product;
import net.atos.mm.formation.tapestry.services.ProductCatalog;

public class Catalog {

	@Property
	private Product currentProduct;

	@Inject
	private ProductCatalog catalog;

	@Inject
	private AssetSource assetSource;

	public List<Product> getProducts() {
		return catalog.getProducts();
	}

	public String getProductImage() {
		return assetSource.getContextAsset(currentProduct.getAssetLocation(),
				null).toClientURL();
	}
	
	@Property
	private String productDescription;
	
	@InjectComponent
    private Zone zoneDescription;
	
	public String getZoneDescriptionId() {
		return zoneDescription.getClientId();
	}
	
	@OnEvent(value=EventConstants.ACTION, component="linkShowDescription")
	public Object showProductDescription(String description) {
		productDescription = description;
		
		return zoneDescription.getBody();
	}
}

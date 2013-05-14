package net.atos.mm.formation.tapestry.pages;

import java.util.List;

import javax.portlet.Event;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.PortletRenderable;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Request;

import com.liferay.portlet.EventImpl;

import net.atos.mm.formation.tapestry.ECommerceConstants;
import net.atos.mm.formation.tapestry.data.Product;
import net.atos.mm.formation.tapestry.model.UserBasket;
import net.atos.mm.formation.tapestry.services.ProductCatalog;

public class Catalog {
	
	@Inject
    private Request request;
	
	@SessionState
	private UserBasket basket;
	
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
	
	public Long getNbItemsInBasket() {
		Long nbItems =  basket.getQuantity(currentProduct.getId());
		return nbItems == null ? 0 : nbItems;
	}
	
	public boolean getIsItemInBasket() {
		Long nbItems = basket.getQuantity(currentProduct.getId());
		return nbItems != null && nbItems > 0;
	}
	
	@Property
	private String productDescription;
	
	@InjectComponent
    private Zone zoneDescription;
	
	public String getZoneDescriptionId() {
		return zoneDescription.getClientId();
	}
	
	/**
	 * Refresh the zone with the description of the selected product.
	 * 
	 * @param description of the product.
	 * 
	 * @return Zone body.
	 */
	@OnEvent(value=EventConstants.ACTION, component="linkShowDescription")
	public Object showProductDescription(String description) {
		productDescription = description;
		
		return zoneDescription.getBody();
	}
	
	/**
	 * Return an event used to warn Basket portlet that a product has been added to the user basket.
	 * 
	 * @param Id of the added product.
	 * 
	 * @return Event
	 */
	@OnEvent(value=EventConstants.ACTION, component="linkAddBasket")
	public Event addProductToBasket(long productId) {
		return (Event) new EventImpl(ECommerceConstants.ADD_TO_BASKET, null, productId);
	}
	
	/**
	 * Remove a product from user basket and returns current portlet with a render parameter.
	 * 
	 * @param Id of the removed product.
	 * @return PortletRenderable
	 */
	@OnEvent(value=EventConstants.ACTION, component="linkRemoveFromBasket")
	public PortletRenderable removeProductFromBasket(long productId) {
		basket.removeFromBasket(productId);
		
		PortletRenderable renderable = new PortletRenderable("Catalog");
		
		//Used to warn Basket portlet that a product has been deleted.
        renderable.addRenderParameter(ECommerceConstants.PRODUCT_REMOVED, String.valueOf(productId));
        
    	return renderable;
	}
}

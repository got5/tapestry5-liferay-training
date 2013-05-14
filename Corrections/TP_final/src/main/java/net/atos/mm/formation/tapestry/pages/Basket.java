package net.atos.mm.formation.tapestry.pages;

import net.atos.mm.formation.tapestry.ECommerceConstants;
import net.atos.mm.formation.tapestry.data.Product;
import net.atos.mm.formation.tapestry.model.UserBasket;
import net.atos.mm.formation.tapestry.services.ProductCatalog;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

public class Basket {
	
	@Inject
    private Request request;
	
	@Inject
	private ProductCatalog catalog;
	
	@SessionState
	private UserBasket basket;
	
	@Property
	private String msgProductRemoved;
	
	@SetupRender
	public void init() {
		//Get render parameter (used if a product is removed from basket) value from request.
		String strRemovedProductId = request.getParameter(ECommerceConstants.PRODUCT_REMOVED);
		if (strRemovedProductId != null) {
			msgProductRemoved = catalog.getProduct(Long.valueOf(strRemovedProductId)).getTitle() + " has been removed from cart.";
		}
	}
	
	/**
     * Handler for portletEvent addTobasket.
     */
    @OnEvent(value=ECommerceConstants.ADD_TO_BASKET)
    public Object onAddToBasket(Long productId)
    {
       basket.addToBasket(productId);
       return this;
    }
    
    public Long getNbItems(){
    	return basket.getNbItems();
    }
    
    @Property
    private Long currentKey;
    
    public Product getCurrentProduct(){
    	return catalog.getProduct(currentKey);    
    }
}

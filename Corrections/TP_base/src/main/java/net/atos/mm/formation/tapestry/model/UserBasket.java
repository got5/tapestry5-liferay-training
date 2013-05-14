package net.atos.mm.formation.tapestry.model;

import java.util.HashMap;
import java.util.Set;

/**
 * User basket.
 */
public class UserBasket {

	private HashMap<Long, Long> productList;

	private long nbItems;

	public UserBasket() {
		super();
		// assume a basket for 10 products at start
		productList = new HashMap<Long, Long>(10);
		// poductId as Long , nb as long
		nbItems = 0;
	}
	
	/**
	 * Add a product in user basket.
	 * 
	 * @param Id of the product to add.
	 */
	public void addToBasket(long ProductId) {
		Long n = productList.get(ProductId);
		if (n == null) {
			productList.put(ProductId, 1L);
		} else {
			productList.put(ProductId, n + 1);
		}
		nbItems++;
	}
	
	/**
	 * Remove a product from the basket.
	 * 
	 * @param Id of the product to remove.
	 */
	public void removeFromBasket(long productId) {
		Long n = productList.get(productId);
		if (n == null) {
			return;
		} else {
			if (n == 0) {
				return;
			}
			productList.put(productId, n - 1);
		}
		nbItems--;
	}
	
	/**
	 * Returns quantity from a given product in user basket.
	 * 
	 * @param Id of the product
	 * @return item quantity
	 */
	public Long getQuantity(long ProductId) {
		return productList.get(ProductId);
	}
	
	/**
	 * Returns items quantity in basket.
	 * 
	 * @return Number of items
	 */
	public Long getNbItems() {
		return nbItems;
	}
	
	/**
	 * Returns ids of all items in basket.
	 * 
	 * @return Ids
	 */
	public Set<Long> getProductIDs() {
		return productList.keySet();
	}
}

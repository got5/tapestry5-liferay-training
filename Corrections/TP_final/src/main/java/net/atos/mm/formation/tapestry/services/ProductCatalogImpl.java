package net.atos.mm.formation.tapestry.services;

import java.util.List;

import net.atos.mm.formation.tapestry.data.Product;

import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

/**
 * ProductCatalog service implementation.
 */
public class ProductCatalogImpl implements ProductCatalog {
	private List<Product> products;
	
	/**
     * Provides a list of all products in an indeterminate order.
     */
	public List<Product> getProducts() {
		return products;
	}

	public ProductCatalogImpl() {
		products = CollectionFactory.newList();

		products.add(new Product(71, "IPad 2", 379.00, "/images/products/iPad2.jpg", "Tablet PC Apple iPad 2 MC979NF/A - 24,6 cm (9,7) LED - Apple A5 1 GHz - Blanc "));
		products.add(new Product(72, "IPad 3", 412.00, "/images/products/iPad3.jpg", "Apple iPad 3eme generation Wifi Ecran Retina LED 9,7\" (24,6 cm) 16 Go Puce bicoeur A5X iSight 5 Mpix Bluetooth Noir"));
		products.add(new Product(73, "IPad 4", 515.00, "/images/products/iPad4.jpg", "Apple iPad 4ème génération - MD511NF/A - Tablette Tactile Retina 9.7\" - WiFi - 32Gb - iOS 6 - Noir"));
	}
	
	/**
     * Gets a product by its unique id.
     *
     * @param id of the product to retrieve
     *
     * @return the Product
     */
	public Product getProduct(long id) {
		for (int index = 0; index < products.size(); index++) {
			Product result = products.get(index);
			if (result.getId() == id) {
				return result;
			}
		}
		return null;
	}

}

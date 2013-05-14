package net.atos.mm.formation.tapestry.services;

import java.util.List;

import net.atos.mm.formation.tapestry.data.Product;

/**
 * Products catalog service.
 */
public interface ProductCatalog
{
    /**
     * Gets a product by its unique id.
     *
     * @param id of the product to retrieve
     *
     * @return the Product
     */
    Product getProduct(long id);

    /**
     * Provides a list of all products in an indeterminate order.
     */
    List<Product> getProducts();

}

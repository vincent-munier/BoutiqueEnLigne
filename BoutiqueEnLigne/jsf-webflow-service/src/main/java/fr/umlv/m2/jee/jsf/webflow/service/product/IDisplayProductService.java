package fr.umlv.m2.jee.jsf.webflow.service.product;

import java.util.List;

import fr.umlv.m2.jee.persistence.product.Product;

/**
 * Created by IntelliJ IDEA.
 * User: gloyaute
 * Date: 6 nov. 2010
 * Time: 21:08:08
 * To change this template use File | Settings | File Templates.
 */
public interface IDisplayProductService {

	/**
	 * Get all id product in base and call the amazon web service for each to retrieve the product
	 * @return a list of product
	 */
    public List<Product> getAllProduct();
    
    /**
     * Get all id product in base for a catagory and call the amazon web service for each to retrieve the product
	 * @param cat the category id
	 * @return a list of product
     */
    public List<Product> getProductByCat(int cat);
}

package fr.umlv.m2.jee.persistence.cart;

import java.util.Map;

import fr.umlv.m2.jee.persistence.product.Product;

public interface CartDao {
	public void addProduct(Product p);
	public void clear();
	public Map<Product, Integer> getAllProduct();
	public void delProduct(Product p);
}

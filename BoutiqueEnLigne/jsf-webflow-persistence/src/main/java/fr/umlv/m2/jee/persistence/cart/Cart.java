package fr.umlv.m2.jee.persistence.cart;

import java.util.HashMap;
import java.util.Map;

import fr.umlv.m2.jee.persistence.product.Product;

public class Cart {
	private Map<Product, Integer> products;
	
	public Cart() {
		products = new HashMap<Product, Integer>();
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}
	
	
}

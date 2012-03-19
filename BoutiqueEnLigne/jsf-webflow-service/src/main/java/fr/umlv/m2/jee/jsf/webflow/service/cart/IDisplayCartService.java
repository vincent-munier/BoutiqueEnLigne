package fr.umlv.m2.jee.jsf.webflow.service.cart;

import java.util.List;

import fr.umlv.m2.jee.persistence.product.Product;

public interface IDisplayCartService {
	public void addProduct(Product p);
	public void clear();
	public List<DisplayProduct> getAllProduct();
	public double getPriceTot();
	public void delProduct(DisplayProduct dp);
}

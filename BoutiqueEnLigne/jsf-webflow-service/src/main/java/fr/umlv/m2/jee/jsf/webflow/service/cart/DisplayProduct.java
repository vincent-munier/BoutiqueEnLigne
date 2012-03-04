package fr.umlv.m2.jee.jsf.webflow.service.cart;

import java.io.Serializable;

public class DisplayProduct implements Serializable{
	private static final long serialVersionUID = 6993652198771971125L;
	private String id;
	private String name;
	private int quantity;
	private double priceTot;
	private String imageUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPriceTot() {
		return priceTot;
	}
	public void setPriceTot(double priceTot) {
		this.priceTot = priceTot;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}

package fr.umlv.m2.jee.webservice.amazon;

import java.util.ArrayList;
import java.util.List;

import com.ECS.client.jax.AWSECommerceServicePortType;

import fr.umlv.m2.jee.persistence.product.Product;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AWSECommerceServicePortType port = UtilsWebService.InitWebService();
		List<String> id = new ArrayList<String>();
		id.add("B002BRZ9G0");
		id.add("B000FQ9R4E");
		id.add("B004PAGJNS");
		id.add("B0065NP39E");
		
		id.add("1401203817");
		id.add("1401206425");	
		List<Product> po = UtilsWebService.getProductById(port, id);
		for(Product p : po){
			System.out.println("Title : "+p.getName());
			System.out.println("Prix : "+p.getPrice());
		}
	}

	
}

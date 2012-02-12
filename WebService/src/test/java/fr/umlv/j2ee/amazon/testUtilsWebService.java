package fr.umlv.j2ee.amazon;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ECS.client.jax.AWSECommerceServicePortType;

import fr.umlv.m2.jee.persistence.product.Product;
import fr.umlv.m2.jee.webservice.amazon.UtilsWebService;

public class testUtilsWebService {
	
	@Test
	public void testInitWebService(){
		AWSECommerceServicePortType port = UtilsWebService.InitWebService();
		assertNotNull(port);
	}
	
	@Test
	public void testGetProductById(){
		AWSECommerceServicePortType port = UtilsWebService.InitWebService();
		List<String> id = new ArrayList<String>();
		id.add("B002BRZ9G0");
		id.add("B000FQ9R4E");
		id.add("B004PAGJNS");
		id.add("B0065NP39E");
		
		id.add("1401203817");
		id.add("1401206425");	
		List<Product> p = UtilsWebService.getProductById(port, id);
		assertNotNull(p);
		assertEquals(id.size(), p.size());
	}

}

package fr.umlv.m2.jee.webservice.amazon;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Holder;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.AWSECommerceServicePortType;
import com.ECS.client.jax.AwsHandlerResolver;
import com.ECS.client.jax.Item;
import com.ECS.client.jax.ItemLookup;
import com.ECS.client.jax.ItemLookupRequest;
import com.ECS.client.jax.Items;
import com.ECS.client.jax.OperationRequest;

import fr.umlv.m2.jee.persistence.category.Category;
import fr.umlv.m2.jee.persistence.product.Product;

/**
 * 
 * @author GOGNIAT Michael
 * 
 * List of function for use the amazon WebService.
 * Use to connect to the webService and retrieve product.	
 */
public class UtilsWebService{
	
	private static final String awsAccessKeyID = "AKIAJEFZOUOLEA3QRIQA";
	private static final String awsSecretKeyID = "KFuPf9eO9qdVqQI8O4WoWf16gLyg+2uAKO2Vvvp9";
	private static final String awsDeveloperID = "boutiqueenlig-20";
	
	/**
	 * Initialize the webservice connexion wth the secret key
	 * @return : the connexion
	 */
	public static AWSECommerceServicePortType InitWebService(){
		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(awsSecretKeyID)); 
		AWSECommerceServicePortType port = service.getAWSECommerceServicePort();
		return port;
	}
	
	/**
	 * Search a product by it's id in the amazon database with the webservice
	 * @param port connexion to the webservice
	 * @param id id of the product
	 * @return the product find. An instance of Product class
	 */
	public static List<Product> getProductById(AWSECommerceServicePortType port, List<String> id){
		List<Product> result = new ArrayList<Product>();
		ItemLookupRequest itemLookup = new ItemLookupRequest();
		itemLookup.getItemId().addAll(id);
		itemLookup.getResponseGroup().add("Medium");		
		ItemLookup lookup = new ItemLookup();
		lookup.setAWSAccessKeyId(awsAccessKeyID);
		lookup.getRequest().add(itemLookup);	
		Holder<OperationRequest> operationrequest = new Holder<OperationRequest>();
		Holder<java.util.List<Items>> items = new Holder<java.util.List<Items>> ();				
		System.out.println(itemLookup.getItemId().size());
		port.itemLookup("", awsAccessKeyID, awsDeveloperID, "", "", itemLookup, lookup.getRequest(), operationrequest, items);
		System.out.println(items.value.size());
		System.out.println(items.value.get(0).getItem().size());
		for(Item item : items.value.get(0).getItem())
			result.add(new Product(item.getASIN(), item.getItemAttributes().getTitle(), item.getItemAttributes().getListPrice().getFormattedPrice(), "", item.getMediumImage().getURL(), new Category(0,"Autres")));
		return result;
	}

}

package fr.umlv.m2.jee.jsf.webflow.service.product.impl;


import com.ECS.client.jax.Collections;
import fr.umlv.m2.jee.persistence.category.Category;
import org.springframework.stereotype.Service;

import com.ECS.client.jax.AWSECommerceServicePortType;

import fr.umlv.m2.jee.persistence.product.Product;
import fr.umlv.m2.jee.persistence.product.ProductDao;
import fr.umlv.m2.jee.persistence.product.ProductDaoImp;
import fr.umlv.m2.jee.webservice.amazon.UtilsWebService;
import fr.umlv.m2.jee.database.cassandra.CassandraUtils;
import fr.umlv.m2.jee.jsf.webflow.service.product.IDisplayProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gloyaute
 * Date: 6 nov. 2010
 * Time: 21:09:50
 * To change this template use File | Settings | File Templates.
 */
@Service("defaultDisplayProductService")
public class DefaultDisplayProductService implements IDisplayProductService {

    private Map<String, Product> products = new HashMap<String, Product>();

    public void init(){
        Product p1 = new Product();
        p1.setId("1401206425");
        p1.setCategory(new Category(0, "Autres"));

        Product p2 = new Product();
        p2.setId("0752866508");
        p2.setCategory(new Category(0, "Autres"));

        Product p3 = new Product();
        p3.setId("B000059MEK");
        p3.setCategory(new Category(0, "Autres"));

        Product p4 = new Product();
        p3.setId("B000FQ9R4E");
        p3.setCategory(new Category(0, "Autres"));

        products.put("1401206425", p1);
        products.put("0752866508", p2);
        products.put("B000059MEK", p3);
        products.put("B000FQ9R4E", p4);
    }

	public List<Product> getAllProduct() {
		/*ProductDao dao = new ProductDaoImp();
		Map<String, Product> products = dao.findAll();
		List<Product> result = new ArrayList<Product>();
		List<String> id = new ArrayList<String>(products.size());
		for(Product p : products.values())
			id.add(p.getId());*/
        List<Product> result = new ArrayList<Product>();
		AWSECommerceServicePortType port = UtilsWebService.InitWebService();
		result.addAll(UtilsWebService.getProductById(port, new ArrayList<String>(products.keySet())));
		return result;
	}

	public List<Product> getProductByCat(int cat) {
		// TODO Auto-generated method stub
		return null;
	}
}

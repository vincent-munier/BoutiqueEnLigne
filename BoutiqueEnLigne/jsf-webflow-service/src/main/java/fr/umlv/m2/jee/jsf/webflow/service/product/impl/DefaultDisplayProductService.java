package fr.umlv.m2.jee.jsf.webflow.service.product.impl;


import org.springframework.stereotype.Service;

import com.ECS.client.jax.AWSECommerceServicePortType;

import fr.umlv.m2.jee.persistence.product.Product;
import fr.umlv.m2.jee.persistence.product.ProductDao;
import fr.umlv.m2.jee.persistence.product.ProductDaoImp;
import fr.umlv.m2.jee.webservice.amazon.UtilsWebService;
import fr.umlv.m2.jee.database.cassandra.CassandraUtils;
import fr.umlv.m2.jee.jsf.webflow.service.product.IDisplayProductService;

import java.util.ArrayList;
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

	@Override
	public List<Product> getAllProduct() {
		ProductDao dao = new ProductDaoImp();
		Map<String, Product> products = dao.findAll();
		List<Product> result = new ArrayList<Product>();
		List<String> id = new ArrayList<String>(products.size());
		for(Product p : products.values())
			id.add(p.getId());
		AWSECommerceServicePortType port = UtilsWebService.InitWebService();
		result.addAll(UtilsWebService.getProductById(port, id));
		return result;
	}

	@Override
	public List<Product> getProductByCat(int cat) {
		// TODO Auto-generated method stub
		return null;
	}

   
}

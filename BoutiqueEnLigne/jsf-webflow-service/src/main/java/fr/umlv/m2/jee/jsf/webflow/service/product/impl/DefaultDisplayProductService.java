package fr.umlv.m2.jee.jsf.webflow.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ECS.client.jax.AWSECommerceServicePortType;

import fr.umlv.m2.jee.jsf.webflow.service.product.IDisplayProductService;
import fr.umlv.m2.jee.persistence.category.Category;
import fr.umlv.m2.jee.persistence.product.Product;
import fr.umlv.m2.jee.persistence.product.ProductDao;
import fr.umlv.m2.jee.persistence.product.ProductDaoImp;
import fr.umlv.m2.jee.webservice.amazon.UtilsWebService;

/**
 * Created by IntelliJ IDEA. User: gloyaute Date: 6 nov. 2010 Time: 21:09:50 To
 * change this template use File | Settings | File Templates.
 */
@Service("defaultDisplayProductService")
public class DefaultDisplayProductService implements IDisplayProductService {

  private Map<String, Product> products = new HashMap<String, Product>();

  public void init() {
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
   List<Product> result = new ArrayList<Product>();
   AWSECommerceServicePortType port = UtilsWebService.InitWebService();
   result.addAll(UtilsWebService.getProductById(port, new ArrayList<String>(
   products.keySet())));
   return result;
   }

  // getAllProduct coded in hard. It does not pass through the Amazon Web
  // Service.
  // public List<Product> getAllProduct() { List<Product> result = new
  // ArrayList<Product>(); Product p1 = new Product("0752866508",
  // "Asterix in Belgium", "$10.95", "",
  // "http://ecx.images-amazon.com/images/I/61TrkGnhDhL._SL160_.jpg", new
  // Category(0, "Autres")); result.add(p1); result.add(p1); return result; }

  // Use the dao to call Cassandra
//  @Override
//  public List<Product> getAllProduct() {
//    ProductDao dao = new ProductDaoImp();
//    Map<String, Product> products = dao.findAll();
//    List<Product> result = new ArrayList<Product>();
//    List<String> id = new ArrayList<String>(products.size());
//    for (Product p : products.values()) 
//      id.add(p.getId());
//   
//    AWSECommerceServicePortType port = UtilsWebService.InitWebService();
//    result.addAll(UtilsWebService.getProductById(port, id));
//    return result;
//  }

  public List<Product> getProductByCat(int cat) {
    // TODO Auto-generated method stub
    return null;
  }
}

package fr.umlv.m2.jee.jsf.webflow.service.cart.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import fr.umlv.m2.jee.jsf.webflow.service.cart.DisplayProduct;
import fr.umlv.m2.jee.jsf.webflow.service.cart.IDisplayCartService;
import fr.umlv.m2.jee.persistence.cart.CartDao;
import fr.umlv.m2.jee.persistence.cart.CartDaoImpl;
import fr.umlv.m2.jee.persistence.product.Product;

@Service("defaultDisplayCartService")
public class DefaultDisplayCartService implements IDisplayCartService {

  private CartDao dao;

  public void init() {
    dao = new CartDaoImpl();
  }

  public void addProduct(Product p) {
    dao.addProduct(p);
  }

  public void clear() {
    dao.clear();
  }

  public List<DisplayProduct> getAllProduct() {
    Map<Product, Integer> products = dao.getAllProduct();
    List<DisplayProduct> displayProducts = new ArrayList<DisplayProduct>();
    for (Entry<Product, Integer> e : products.entrySet()) {
      DisplayProduct dp = new DisplayProduct();
      dp.setId(e.getKey().getId());
      dp.setImageUrl(e.getKey().getImageUrl());
      dp.setName(e.getKey().getName());
      dp.setQuantity(e.getValue());
      dp.setPriceTot(Double.valueOf(e.getKey().getPrice().substring(1))
          * dp.getQuantity());
      displayProducts.add(dp);
    }
    return displayProducts;
  }

  public void delProduct(DisplayProduct dp) {
    Product p = new Product();
    p.setId(dp.getId());
    dao.delProduct(p);
  }

}

package fr.umlv.m2.jee.persistence.product;

import java.util.Map;



public interface ProductDao {
  public void save(Product product);
  public Product find(String productId);
  public Map<String, Product> findAll();
  public void delete(Product product);
}

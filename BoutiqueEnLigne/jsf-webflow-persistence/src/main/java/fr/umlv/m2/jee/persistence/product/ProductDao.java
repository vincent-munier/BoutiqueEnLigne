package fr.umlv.m2.jee.persistence.product;

import java.util.List;

import fr.umlv.m2.jee.database.cassandra.IDao;

public interface ProductDao extends IDao<String, Product> {
  List<Product> findAllByCategoryId(long categoryId);
  void delete(Product product);
}

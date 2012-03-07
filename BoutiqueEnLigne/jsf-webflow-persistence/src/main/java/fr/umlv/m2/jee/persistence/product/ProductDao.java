package fr.umlv.m2.jee.persistence.product;

import java.util.List;

import fr.umlv.m2.jee.database.cassandra.IDao;

public interface ProductDao extends IDao<String, Product> {
  List<Product> findAllByCategoryId(long categoryId);
  List<Product> findAllByCommandId(long commandId);
  void delete(Product product);
}

package fr.umlv.m2.jee.persistence.product;

import fr.umlv.m2.jee.database.cassandra.IDao;

public interface ProductDao extends IDao<String, Product> {
  void delete(Product product);
}

package fr.umlv.m2.jee.persistence.product;

import fr.umlv.m2.jee.database.cassandra.AbstractColumnFamilyDao;


public class ProductDaoImp extends AbstractColumnFamilyDao<String, Product> implements ProductDao {
  public ProductDaoImp() {
    super(String.class, Product.class, "Products");
  }

  public void save(Product product) {
    super.save(product.getId(), product);
  }

  public void delete(Product product) {
    delete(product.getId());
  }

}



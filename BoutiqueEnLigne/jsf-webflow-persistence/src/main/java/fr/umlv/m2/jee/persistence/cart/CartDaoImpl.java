package fr.umlv.m2.jee.persistence.cart;

import java.util.Map;

import fr.umlv.m2.jee.persistence.product.Product;

public class CartDaoImpl implements CartDao {

  private Cart c;

  public CartDaoImpl() {
    c = new Cart();
  }

  @Override
  public void addProduct(Product p) {
    if (c.getProducts().containsKey(p))
      c.getProducts().put(p, c.getProducts().get(p) + 1);
    else
      c.getProducts().put(p, 1);
  }

  @Override
  public Map<Product, Integer> getAllProduct() {
    return c.getProducts();
  }

  @Override
  public void delProduct(Product p) {
    Product del = null;
    for (Product po : c.getProducts().keySet()) {
      if (po.getId().equals(p.getId())) {
        del = po;
        break;
      }
    }
    c.getProducts().remove(del);
  }

}

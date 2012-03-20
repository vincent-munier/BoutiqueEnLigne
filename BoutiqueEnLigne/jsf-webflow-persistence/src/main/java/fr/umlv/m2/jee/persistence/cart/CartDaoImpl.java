package fr.umlv.m2.jee.persistence.cart;

import java.util.Map;

import fr.umlv.m2.jee.persistence.product.Product;

public class CartDaoImpl implements CartDao {

  private Cart cart;

  public CartDaoImpl() {
    cart = new Cart();
  }

  @Override
  public void addProduct(Product p) {
    if (cart.getProducts().containsKey(p))
      cart.getProducts().put(p, cart.getProducts().get(p) + 1);
    else
      cart.getProducts().put(p, 1);
  }

  @Override
  public Map<Product, Integer> getAllProduct() {
    return cart.getProducts();
  }

  @Override
  public void delProduct(Product p) {
    Product del = null;
    for (Product po : cart.getProducts().keySet()) {
      if (po.getId().equals(p.getId())) {
        del = po;
        break;
      }
    }
    cart.getProducts().remove(del);
  }

  @Override
  public void clear() {
    cart.clear();
  }

  @Override
  public void updateProduct(Product p, int val) {
    Product update = null;
    for (Product po : cart.getProducts().keySet()) {
      if (po.getId().equals(p.getId())) {
        update = po;
        break;
      }
    }
    cart.getProducts().put(update, val);
  }


}

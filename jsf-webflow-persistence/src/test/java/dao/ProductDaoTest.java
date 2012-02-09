package dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.umlv.m2.jee.persistence.product.Product;
import fr.umlv.m2.jee.persistence.product.ProductDao;
import fr.umlv.m2.jee.persistence.product.ProductDaoImp;

public class ProductDaoTest {
  private ProductDao productDao = new ProductDaoImp();

  private final String[] iDs = { "A144GEAD56", "D329FEJD7M", "FE14G74ED" };
  // "Tv Hd" and "Buzz l'Éclair" already exists at indices iDs[0] and
  // iDs[1] respectively;
  // "Ordi Asus" is a new one which doesn't exist yet.
  private final String[] productNames = { "Tv Hd", "Buzz l'Éclair", "Ordi Asus" };
  private final String[] productDescriptions = { "Écran géant comme au cinéma",
      "Buzz vole pour de vrai !", "Nouveau processeur Intel 2970-QM" };
  private final double[] productPrices = { 360, 59.99, 649.98 };

  private Product newProduct;

  @Before
  public void before() {
    newProduct = new Product();
    int idx = 2;
    newProduct.setId(iDs[idx]);
    newProduct.setName(productNames[idx]);
    newProduct.setDescription(productDescriptions[idx]);
    newProduct.setPriceDouble(productPrices[idx]);
  }

  @Test
  public void insertData() {
    productDao.save(newProduct);
  }

  @Test
  public void retrieveData() {
    // retrieve the new one which has just been inserted
    int idx = 0;
    Product productFound = productDao.find(iDs[idx]);
    checkConsistency(productFound, idx);

    // retrieve the old one
    idx = 2;
    productFound = productDao.find(iDs[idx]);
    checkConsistency(productFound, idx);
  }

  public void checkConsistency(Product product, int idx) {
    Assert.assertNotNull(product);
    Assert.assertEquals(productNames[idx], product.getName());
    Assert.assertEquals(productDescriptions[idx], product.getDescription());

    // Epsilon is the value that the 2 numbers can be off by. So it will assert
    // to true as long as Math.abs(expected - actual) < epsilon
    Assert.assertEquals(productPrices[idx], product.getPriceDouble(), 500000);
  }

  @Test
  public void retrieveAll() {
    Map<String, Product> map = productDao.findAll();

    for (int i = 0; i < productNames.length; i++) {
      checkConsistency(map.get(iDs[i]), i);
    }
  }

  @Test
  public void deleteData() {
    productDao.delete(newProduct);
    Product productFound = productDao.find(newProduct.getId());
    Assert.assertNull(productFound);
  }

}

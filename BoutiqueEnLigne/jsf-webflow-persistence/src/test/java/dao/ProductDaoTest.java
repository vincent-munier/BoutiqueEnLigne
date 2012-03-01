//package dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import fr.umlv.m2.jee.persistence.category.Category;
//import fr.umlv.m2.jee.persistence.product.Product;
//import fr.umlv.m2.jee.persistence.product.ProductDao;
//import fr.umlv.m2.jee.persistence.product.ProductDaoImp;
//
//public class ProductDaoTest {
//  private ProductDao productDao = new ProductDaoImp();
//
//  private final String[] iDs = { "A144GEAD56", "D329FEJD7M" };
//  // "Tv Hd" and "Buzz l'Éclair" already exists at indices iDs[0] and
//  // iDs[1] respectively;
//  private final String[] productNames = { "Tv Hd", "Buzz l'Éclair" };
//  private final String[] productDescriptions = { "Écran géant comme au cinéma",
//      "Buzz vole pour de vrai !" };
//  private final double[] productPrices = { 360, 59.99 };
//  private final String[] productCategoryNamesAttached = { "TV", "Autres" };
//
//  private Product newProduct;
//  private String newProductId = "FE14G74ED";
//  private String newProductName = "Ordi Asus";
//  private String newProductDescription = "Nouveau processeur Intel 2970-QM";
//  private double newProductPrice = 649.98;
//  private String newProductCategoryNameAttached = "Autres";
//
//  @Before
//  public void before() {
//    newProduct = new Product();
//
//    newProduct.setId(newProductId);
//    newProduct.setName(newProductName);
//    newProduct.setDescription(newProductDescription);
//    newProduct.setPriceDouble(newProductPrice);
//    newProduct.setCategory(new Category(0, "Autres"));
//  }
//
//  @Test
//  public void insertData() {
//    productDao.save(newProduct);
//  }
//
//  @Test
//  public void retrieveData() {
//    // retrieve the old one
//    int idx = 0;
//    Product productFound = productDao.find(iDs[idx]);
//    checkConsistency(productFound, idx);
//
//    // retrieve the new one which has just been inserted
//    productFound = productDao.find(newProductId);
//    checkFields(productFound, newProductName, newProductDescription,
//        newProductPrice, newProductCategoryNameAttached);
//  }
//
//  public void checkFields(Product product, String productName,
//      String productDescription, double productPrice,
//      String categoryNameAttached) {
//    Assert.assertNotNull(product);
//    Assert.assertEquals(productName, product.getName());
//    Assert.assertEquals(productDescription, product.getDescription());
//
//    // Epsilon is the value that the 2 numbers can be off by. So it will assert
//    // to true as long as Math.abs(expected - actual) < epsilon
//    Assert.assertEquals(productPrice, product.getPriceDouble(), 500000);
//
//    Assert.assertNotNull(product.getCategory());
//    Assert.assertEquals(product.getCategory().getName(), categoryNameAttached);
//  }
//
//  public void checkConsistency(Product product, int idx) {
//    checkFields(product, productNames[idx], productDescriptions[idx],
//        productPrices[idx], productCategoryNamesAttached[idx]);
//  }
//
//  @Test
//  public void retrieveAll() {
//    Map<String, Product> map = productDao.findAll();
//
//    for (int i = 0; i < productNames.length; i++) {
//      checkConsistency(map.get(iDs[i]), i);
//    }
//  }
//
//  @Test
//  public void findAllByCategoryIdTest() {
//    String categoryName = "Autres";
//    long categoryId = 0;
//
//    List<Product> products = productDao.findAllByCategoryId(categoryId);
//
//    Assert.assertTrue(products.size() >= 1);
//    for (Product product : products) {
//      Category categoryAttached = product.getCategory();
//      Assert.assertNotNull(categoryAttached);
//      Assert.assertEquals(categoryId, categoryAttached.getId());
//      Assert.assertEquals(categoryName, categoryAttached.getName());
//    }
//  }
//
//  @Test
//  public void deleteData() {
//    productDao.delete(newProduct);
//    Product productFound = productDao.find(newProduct.getId());
//    Assert.assertNull(productFound);
//  }
//
//}

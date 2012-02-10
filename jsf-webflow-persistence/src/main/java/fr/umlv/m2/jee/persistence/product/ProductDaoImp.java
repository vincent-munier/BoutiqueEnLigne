package fr.umlv.m2.jee.persistence.product;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.LongSerializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;
import fr.umlv.m2.jee.database.cassandra.AbstractColumnFamilyDao;
import fr.umlv.m2.jee.persistence.category.Category;
import fr.umlv.m2.jee.persistence.category.CategoryDao;
import fr.umlv.m2.jee.persistence.category.CategoryDaoImp;

public class ProductDaoImp extends AbstractColumnFamilyDao<String, Product>
    implements ProductDao {

  private LongSerializer longSerializer = LongSerializer.get();
  private Mutator<Long> categoryProductsMutator = HFactory.createMutator(
      keyspace, longSerializer);
  private CategoryDao categoryDao = new CategoryDaoImp();

  public ProductDaoImp() {
    super(String.class, Product.class, "Products");
  }

  public void save(Product product) {
    super.save(product.getId(), product);
    saveProductToCategoryProducts(product);
  }

  private void saveProductToCategoryProducts(Product product) {
    Category category = product.getCategory();
    if (category == null) {
      throw new RuntimeException("product.getCategory() should not be null");
    }

    HColumn<String, ?> column = HFactory.createColumn(product.getId(), "",
        stringSerializer, stringSerializer);

    categoryProductsMutator.addInsertion(category.getId(), "CategoryProducts",
        column);
  }

  public Product find(String productId) {
    Product product = super.find(productId);
    if (product != null) {
      Category category = categoryDao.find(product.getCategoryId());

      if (category == null) {
        throw new RuntimeException("found a product with productId="
            + productId + " but its categoryId=" + product.getCategoryId()
            + " has no match in Category");
      }
      product.setCategory(category);
    }

    return product;
  }

  public List<Product> findAllByCategoryId(long categoryId) {
    SliceQuery<Long, String, byte[]> query = HFactory.createSliceQuery(
        keyspace, longSerializer, stringSerializer, bytesSerializer);

    QueryResult<ColumnSlice<String, byte[]>> result = query
        .setColumnFamily("CategoryProducts").setKey(categoryId)
        .setColumnNames(allColumnNames).execute();

    List<Product> productsOfCategory = new ArrayList<Product>();

    for (HColumn<String, byte[]> col : result.get().getColumns()) {
      String productId = col.getName();
      Product product = find(productId);
      if (product == null)
        throw new RuntimeException("No product with productId=" + productId
            + " found in Products whereas it exists for categoryId="
            + categoryId + " in CategoryProducts");
      
      productsOfCategory.add(product);
    }

    return productsOfCategory;
  }

  public void delete(Product product) {
    delete(product.getId());
    deleteProductFromCategoryProducts(product);
  }

  private void deleteProductFromCategoryProducts(Product product) {
    Category category = product.getCategory();
    if (category != null) {
      categoryProductsMutator.delete(category.getId(), "CategoryProducts",
          null, longSerializer);
    }

  }
}

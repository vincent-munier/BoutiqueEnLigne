//package dao;
//
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import fr.umlv.m2.jee.persistence.category.Category;
//import fr.umlv.m2.jee.persistence.category.CategoryDao;
//import fr.umlv.m2.jee.persistence.category.CategoryDaoImp;
//
//public class CategoryDaoTest {
//
//  private CategoryDao categoryDao = new CategoryDaoImp();
//
//  // categoryNames which already exist
//  private final String[] categoryNames = { "Autres", "Livres", "DVD", "TV" };
//
//  private Category newCategory;
//  private String newCategoryName = "Films";
//  private long newCategoryIdx = -1;
//
//  @Before
//  public void before() {
//    newCategory = new Category();
//    newCategory.setName(newCategoryName);
//    newCategory.setId(newCategoryIdx);
//  }
//
//  @Test
//  public void insertData() {
//    categoryDao.save(newCategory);
//  }
//
//  @Test
//  public void retrieveData() {
//    // retrieve an old one
//    long idx = 1;
//    Category catFound = categoryDao.find(idx);
//    checkConsistency(catFound, idx);
//
//    // retrieve the new one which has just been inserted
//    catFound = categoryDao.find(newCategoryIdx);
//    checkConsistency(catFound, newCategoryIdx);
//  }
//
//  public void checkConsistency(Category category, long idx) {
//    Assert.assertNotNull(category);
//    if (idx == newCategoryIdx)
//      Assert.assertEquals(newCategoryName, category.getName());
//    else
//      Assert.assertEquals(categoryNames[(int) idx], category.getName());
//  }
//
//  @Test
//  public void retrieveAll() {
//    Map<Long, Category> map = categoryDao.findAll();
//
//    Assert.assertEquals(map.size(), categoryNames.length + 1);
//    checkConsistency(map.get(newCategoryIdx), newCategoryIdx);
//    for (long i = 0; i < categoryNames.length; i++) {
//      checkConsistency(map.get(i), i);
//    }
//  }
//
//  @Test
//  public void deleteData() {
//    categoryDao.delete(newCategory);
//    Category catFound = categoryDao.find(newCategory.getId());
//    Assert.assertNull(catFound);
//  }
//}

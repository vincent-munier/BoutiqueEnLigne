package dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.umlv.m2.jee.persistence.category.Category;
import fr.umlv.m2.jee.persistence.category.CategoryDao;
import fr.umlv.m2.jee.persistence.category.CategoryDaoImp;

public class CategoryDaoTest {

  private CategoryDao categoryDao = new CategoryDaoImp();

  // "Livres" and "DVD" already exists at indices 0 and 1 respectively;
  // "Films" is a new one which doesn't exist yet.
  private final String[] categoryNames = { "Livres", "DVD", "Films" };

  private Category newCategory;

  @Before
  public void before() {
    newCategory = new Category();
    int idx = 2;
    newCategory.setName(categoryNames[idx]);
    newCategory.setId(idx);
  }

  @Test
  public void insertData() {

    categoryDao.save(newCategory);
  }

  @Test
  public void retrieveData() {
    // retrieve the new one which has just been inserted
    long idx = 0;
    Category catFound = categoryDao.find(idx);
    checkConsistency(catFound, idx);

    // retrieve the old one
    idx = 2;
    catFound = categoryDao.find(idx);
    checkConsistency(catFound, idx);
  }

  public void checkConsistency(Category category, long idx) {
    Assert.assertNotNull(category);
    Assert.assertEquals(categoryNames[(int)idx], category.getName());
  }

  @Test
  public void retrieveAll() {
    Map<Long, Category> map = categoryDao.findAll();

    Assert.assertEquals(map.size(), categoryNames.length);
    for (long i = 0; i < categoryNames.length; i++) {
      checkConsistency(map.get(i), i);
    }
  }

  @Test
  public void deleteData() {
    categoryDao.delete(newCategory);
    Category catFound = categoryDao.find(newCategory.getId());
    Assert.assertNull(catFound);
  }
}

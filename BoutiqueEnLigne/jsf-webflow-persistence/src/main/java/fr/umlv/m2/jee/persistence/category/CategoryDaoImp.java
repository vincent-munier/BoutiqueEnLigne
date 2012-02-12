package fr.umlv.m2.jee.persistence.category;

import fr.umlv.m2.jee.database.cassandra.AbstractColumnFamilyDao;


public class CategoryDaoImp extends AbstractColumnFamilyDao<Long, Category> implements CategoryDao {
  public CategoryDaoImp() {
    super(Long.class, Category.class, "Categories");
  }

  public void save(Category category) {
    super.save(category.getId(), category);
  }

  public void delete(Category category) {
    delete(category.getId());
  }

}

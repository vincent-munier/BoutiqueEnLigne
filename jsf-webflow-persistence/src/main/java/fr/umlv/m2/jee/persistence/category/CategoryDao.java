package fr.umlv.m2.jee.persistence.category;

import java.util.Map;

public interface CategoryDao {
  public void save(Category category);
  public Category find(Long categoryId);
  public Map<Long, Category> findAll();
  public void delete(Category category);
}

package fr.umlv.m2.jee.persistence.category;

import fr.umlv.m2.jee.database.cassandra.IDao;

public interface CategoryDao extends IDao<Long, Category> {
  void delete(Category category);
}

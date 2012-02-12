package fr.umlv.m2.jee.database.cassandra;

import java.util.Map;

public interface IDao<KeyType, Entity> {
  void save(Entity entity);

  Entity find(KeyType entityId);

  Map<KeyType, Entity> findAll();

  void delete(KeyType key);
}

package fr.umlv.m2.jee.database.cassandra;

import java.util.HashMap;
import java.util.Map;

import me.prettyprint.cassandra.model.CqlQuery;
import me.prettyprint.cassandra.model.CqlRows;
import me.prettyprint.cassandra.serializers.BytesArraySerializer;
import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

public abstract class AbstractColumnFamilyDao<KeyType, T> {
  protected final Class<T> persistentClass;
  protected final Class<KeyType> keyTypeClass;
  protected final Serializer<KeyType> keySerializer;

  protected final Keyspace keyspace;
  protected final String columnFamilyName;

  protected final String[] allColumnNames;
  protected static final StringSerializer stringSerializer = StringSerializer
      .get();
  protected static final BytesArraySerializer bytesSerializer = BytesArraySerializer
      .get();

  public AbstractColumnFamilyDao(Class<KeyType> keyTypeClass,
      Class<T> persistentClass, String columnFamilyName) {
    keyspace = CassandraInit.getInstance().getKeyspace();
    keySerializer = SerializerTypeInferer.getSerializer(keyTypeClass);
    this.keyTypeClass = keyTypeClass;
    this.persistentClass = persistentClass;
    this.columnFamilyName = columnFamilyName;
    this.allColumnNames = DaoHelper.getAllColumnNames(persistentClass);

  }

  public void save(KeyType key, T model) {

    Mutator<KeyType> mutator = HFactory.createMutator(keyspace, keySerializer);
    for (HColumn<?, ?> column : DaoHelper.getColumns(model)) {
      mutator.addInsertion(key, columnFamilyName, column);
    }

    mutator.execute();
  }

  public T find(KeyType key) {
    SliceQuery<KeyType, String, byte[]> query = HFactory.createSliceQuery(
        keyspace, keySerializer, stringSerializer, bytesSerializer);

    QueryResult<ColumnSlice<String, byte[]>> result = query
        .setColumnFamily(columnFamilyName).setKey(key)
        .setColumnNames(allColumnNames).execute();

    if (result.get().getColumns().size() == 0) {
      return null;
    }

    try {
      T t = persistentClass.newInstance();
      DaoHelper.populateEntity(t, result);
      return t;
    } catch (Exception e) {
      throw new RuntimeException("Error creating persistent class", e);
    }
  }

  public Map<KeyType, T> findAll() {
    Map<KeyType, T> map = new HashMap<KeyType, T>();

    CqlQuery<KeyType, String, byte[]> cqlQuery = new CqlQuery<KeyType, String, byte[]>(
        keyspace, keySerializer, stringSerializer, bytesSerializer);
    cqlQuery.setQuery("SELECT * FROM " + columnFamilyName);
    QueryResult<CqlRows<KeyType, String, byte[]>> res = cqlQuery.execute();

    CqlRows<KeyType, String, byte[]> rows = res.get();
    System.out.println(rows.getList().size());
    for (Row<KeyType, String, byte[]> row : rows.getList()) {
      System.out.print("row key : " + row.getKey() + ", cols : [");

      KeyType key = row.getKey();
      map.put(key, find((KeyType)key));
    }
    return map;
    //
    // CqlQuery<KeyType, String, byte[]> cqlQuery = new CqlQuery<KeyType,
    // String, byte[]>(
    // keyspace, keySerializer, stringSerializer, bytesSerializer);
    // cqlQuery.setQuery("SELECT * FROM " + columnFamilyName);
    // System.out.println("SELECT * FROM " + columnFamilyName);
    // QueryResult<CqlRows<KeyType, String, byte[]>> res = cqlQuery.execute();
    //
    // CqlRows<KeyType, String, byte[]> rows = res.get();
    //
    // for (Row<KeyType, String, byte[]> row : rows.getList()) {
    // System.out.print("row key : " + row.getKey() + ", cols : [");
    //
    // KeyType key = row.getKey();
    // map.put(key, find(key));
    // System.out.println("value insert in map : " + map.get(key));
    // }
    // return map;
  }

  public void delete(KeyType key) {
    Mutator<KeyType> mutator = HFactory.createMutator(keyspace, keySerializer);
    mutator.delete(key, columnFamilyName, null, keySerializer);
  }
}

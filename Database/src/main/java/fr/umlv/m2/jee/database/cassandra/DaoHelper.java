package fr.umlv.m2.jee.database.cassandra;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;

public final class DaoHelper {

  private DaoHelper() {
  }

  public static java.util.UUID getTimeUUID() {
    return java.util.UUID.fromString(new com.eaio.uuid.UUID().toString());
  }

  public static byte[] asByteArray(java.util.UUID uuid) {
    long msb = uuid.getMostSignificantBits();
    long lsb = uuid.getLeastSignificantBits();
    byte[] buffer = new byte[16];

    for (int i = 0; i < 8; i++) {
      buffer[i] = (byte) (msb >>> 8 * (7 - i));
    }
    for (int i = 8; i < 16; i++) {
      buffer[i] = (byte) (lsb >>> 8 * (7 - i));
    }

    return buffer;
  }

  // find all fields of entity by reflection and build a HColumn for each of
  // them where the column's name is the field's name and column's value is the
  // field's value.
  // Return the list of all these built columns based on entity's fields.
  public static <T> List<HColumn<String, ?>> getColumns(T entity) {
    try {
      List<HColumn<String, ?>> columns = new ArrayList<HColumn<String, ?>>();
      Field[] fields = entity.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(entity);

        if (value == null) {
          // Field has no value so nothing to store
          continue;
        }
        String name = field.getName();

        Serializer<Object> serializer = SerializerTypeInferer
            .getSerializer(value);
        
        boolean notAPrimitiveType = serializer == null;
        if (notAPrimitiveType)
          continue;
        
        HColumn<String, ?> column = HFactory.createColumn(name, value,
            StringSerializer.get(), serializer);

        columns.add(column);
      }
      return columns;
    } catch (Exception e) {
      throw new RuntimeException("Reflecting yeee haw exception", e);
    }
  }

  public static <T> List<HColumn<String, String>> getStringCols(T entity) {
    try {
      List<HColumn<String, ?>> cols = getColumns(entity);
      List<HColumn<String, String>> retCols = new ArrayList<HColumn<String, String>>();

      for (HColumn<String, ?> col : cols) {
        retCols.add(HFactory.createStringColumn(col.getName(), col.getValue()
            .toString()));
      }

      return retCols;
    } catch (Exception e) {
      throw new RuntimeException("Reflecting away", e);
    }
  }

  // fill the entity with columns in result.
  // For each entity's field, if the field's name corresponds to a column's name
  // in existing in result then the field's value is set with the column's
  // value. Otherwise the field's value is discarded.
  public static <T> void populateEntity(T entity,
      QueryResult<ColumnSlice<String, byte[]>> result) {
    try {
      Field[] fields = entity.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        String name = field.getName();
        HColumn<String, byte[]> col = result.get().getColumnByName(name);
        if (col == null || col.getValue() == null || col.getValue().length == 0) {
          // No data for this col, the field's value is discarded
          continue;
        }

        Object val = SerializerTypeInferer.getSerializer(field.getType())
            .fromBytes(col.getValue());
        field.set(entity, val);
      }
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Error Reflecting", e);
    }
  }

  public static <T> Field getFieldForPropertyName(T entity, String name) {
    try {
      return entity.getClass().getDeclaredField(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void populateEntityFromCols(Object entity,
      List<HColumn<String, String>> cols) {

    for (HColumn<String, ?> col : cols) {
      Field f = getFieldForPropertyName(entity, col.getName());
      try {
        f.setAccessible(true);
        f.set(entity, col.getValue());
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static String[] getAllColumnNames(Class<?> entityClass) {
    List<String> columnNames = new ArrayList<String>();
    Field[] fields = entityClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      String name = field.getName();
      columnNames.add(name);
    }

    return columnNames.toArray(new String[] {});
  }
}

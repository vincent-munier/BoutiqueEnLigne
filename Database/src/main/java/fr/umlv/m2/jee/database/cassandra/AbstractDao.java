package fr.umlv.m2.jee.database.cassandra;

import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;

public class AbstractDao {
  // The serializers translates the byte[] to and from type (string, int...)
  // using utf-8 encoding
  protected static StringSerializer stringSerializer = StringSerializer.get();
  protected static IntegerSerializer intSerializer = IntegerSerializer.get();
  protected final Keyspace keySpace;

  public AbstractDao(Keyspace keySpace) {
    this.keySpace = keySpace;
  }
}

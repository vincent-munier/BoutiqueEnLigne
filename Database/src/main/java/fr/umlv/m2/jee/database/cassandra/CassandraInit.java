package fr.umlv.m2.jee.database.cassandra;

import java.io.IOException;
import java.util.Properties;

import me.prettyprint.cassandra.model.ConfigurableConsistencyLevel;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

class CassandraInit {
  private Cluster cluster;
  private Keyspace keyspace;
  private Properties properties;

  private final String clusterName = "BoutiqueCluster";
  private final String clusterHosts = "127.0.0.1:9160";
  
  static private CassandraInit instance = new CassandraInit();

  static public CassandraInit getInstance() {
    return instance;
  }

  public Keyspace getKeyspace() {
    return keyspace;
  }

  private CassandraInit() {
    properties = new Properties();
    try {
      properties.load(CassandraInit.class
          .getResourceAsStream("/cassandra.properties"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    cluster = HFactory.getOrCreateCluster(
        properties.getProperty("cluster.name", clusterName),
        properties.getProperty("cluster.hosts", clusterHosts));
    ConfigurableConsistencyLevel ccl = new ConfigurableConsistencyLevel();
    ccl.setDefaultReadConsistencyLevel(HConsistencyLevel.ONE);
    keyspace = HFactory.createKeyspace(
        properties.getProperty("boutique.keyspace", "BoutiqueKeyspace"),
        cluster, ccl);
  }

}

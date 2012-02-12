package dao;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import service.TestCassandraServer;

public abstract class AbstractDaoTest {
  private static final String BLOG_CLUSTER = "Test Cluster";
  private static final String KEY_SPACE = "Keyspace1";

  private Cluster cluster;
  protected Keyspace keySpace;
  private static TestCassandraServer server;

  @BeforeClass
  public static void beforeClass() throws Exception {
    //BootStrap.init();
  }

  @AfterClass
  public static void afterClass() throws Exception {
    if (server != null) {
      //server.teardown();
    }
  }

  @Before
  public void before() {
    cluster = HFactory.getOrCreateCluster(BLOG_CLUSTER, "localhost:9160");
    keySpace = HFactory.createKeyspace(KEY_SPACE, cluster);
  }

}

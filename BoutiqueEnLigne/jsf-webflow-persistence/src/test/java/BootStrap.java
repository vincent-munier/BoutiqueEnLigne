
import service.TestCassandraServer;


public class BootStrap {
  private static volatile boolean started;
  private static Object monitor = new Object();
  private static TestCassandraServer server;
  public static void init() {
    if (started) {
      return;
    }
    synchronized (monitor) {
      server = new TestCassandraServer();
      try {
        //server.setup(); 
        started = true;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}

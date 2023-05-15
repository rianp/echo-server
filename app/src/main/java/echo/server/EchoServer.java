package echo.server;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
  public static ServerSocket startServer(int port) {
    ServerSocket result;
    try {
      result = createSocket(port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  protected static ServerSocket createSocket(int port) throws IOException {
    return new ServerSocket(port);
  }
}

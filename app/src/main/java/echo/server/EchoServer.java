/*
 *
 */

package echo.server;

import java.io.IOException;
import java.net.ServerSocket;

import java.net.Socket;

public class EchoServer {
  public static ServerSocket startServerSocket(int port) {
    ServerSocket serverSocket;
    try {
      serverSocket = createSocket(port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return serverSocket;
  }

  public static Socket acceptClientSocket(ServerSocket server) {
    Socket clientSocket;
    try {
      clientSocket = server.accept();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return clientSocket;
  }

  protected static ServerSocket createSocket(int port) throws IOException {
    return new ServerSocket(port);
  }
}

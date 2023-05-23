/*
 *
 */

package echo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public ServerSocket startServerSocket(int port) {
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return serverSocket;
  }

  public Socket acceptClientConnectionRequest(ServerSocket server) {
    Socket clientSocket;
    try {
      clientSocket = server.accept();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return clientSocket;
  }
}

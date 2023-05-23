/*
 *
 */

package echo.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
  public Socket requestSocket(int port) {
    Socket clientSocket;
    try {
      clientSocket = new Socket("localhost", port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return clientSocket;
  }
}


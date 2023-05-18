/*
 *
 */

package echo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
  public static Socket createSocket(int port) {
    Socket socket;
    try {
      socket = new Socket("localhost", port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return socket;
  }

  public static PrintWriter sendClientMessageToServer(Socket socket, String message) {

    try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
      out.print(message);
      return out;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}


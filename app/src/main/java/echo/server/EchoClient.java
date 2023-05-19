/*
 *
 */

package echo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
  public static Socket requestSocket(int port) {
    Socket clientSocket;
    try {
      clientSocket = new Socket("localhost", port);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return clientSocket;
  }

  public static void sendClientMessageToServer(Socket socket, String message) {

    try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
      out.print(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


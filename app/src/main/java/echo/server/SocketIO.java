/*
 *
 */

package echo.server;

import java.io.*;
import java.net.Socket;

public class SocketIO {
  public static String readMessage(Socket serverConnection) {
    String string = "";
    try {
      InputStream inputStream = serverConnection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      string = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return string;
  }

  public static void sendMessage(Socket socket, String message) {

    try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
      out.print(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

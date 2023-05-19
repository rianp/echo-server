/*
 *
 */

package echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketIO {
  public static String readClientMessage(Socket serverConnection) {
    String string = "";
    try (BufferedReader out = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()))) {
      string = out.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return string;
  }
}

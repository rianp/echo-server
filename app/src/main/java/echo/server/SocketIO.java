/*
 *
 */

package echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

  public static PrintWriter createSocketWriter(Socket socket) throws IOException {
    return new PrintWriter(socket.getOutputStream(), true);
  }

  public static void writeToOutputStream(PrintWriter output, String data) {
    output.println(data);
  }
}

/*
 *
 */

package echo.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketIO {
  public static Scanner createSocketReader(Socket socket) throws IOException {
    return new Scanner(new InputStreamReader(
        socket.getInputStream()));
  }

  public static String readFromInputStream(Scanner input) {
    return input.nextLine();
  }

  public static PrintWriter sendMessage(Socket socket, String message) {

    try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
      out.print(message);
      return out;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

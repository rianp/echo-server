/*
 *
 */

package echo.server;

import java.io.IOException;
import java.io.InputStreamReader;
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
}

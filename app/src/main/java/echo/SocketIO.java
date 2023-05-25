package echo;

import java.io.*;
import java.net.Socket;

public class SocketIO {

  public String readMessage(Socket serverConnection) {
    String string = "";
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
      string = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return string;
  }

  public void sendMessage(Socket socket, String message) throws IOException {

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    out.println(message);
  }
}

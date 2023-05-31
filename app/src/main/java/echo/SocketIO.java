package echo;

import java.io.*;
import java.net.Socket;

public class SocketIO {

  public String readMessage(Socket serverConnection) {
    String message = "";
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
      message = in.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return message;
  }

  public void sendMessage(Socket socket, String message) throws IOException {

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    out.println(message);
  }
}

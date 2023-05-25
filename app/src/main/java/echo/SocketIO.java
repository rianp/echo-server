package echo;

import java.io.*;
import java.net.Socket;

public class SocketIO {
  public String readMessage(Socket serverConnection) {
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

  public void sendMessage(Socket socket, String message) throws IOException {

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    out.println(message);
  }
}

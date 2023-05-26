package echo.client;

import echo.Console;
import echo.SocketIO;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
  private final Console console;
  private final Socket socket;
  private final SocketIO socketIO;

  public EchoClient(Console console, Socket socket, SocketIO socketIO) {
    this.console = console;
    this.socket = socket;
    this.socketIO = socketIO;
  }

  public void start() throws IOException {
    while (true) {
      String message = console.inputString("Enter a message to echo, or 'quit' to exit: ");
      if ("quit".equals(message)) {
        break;
      }
      socketIO.sendMessage(socket, message);
      String echo = socketIO.readMessage(socket);
      console.print(echo);
    }
    socket.close();
  }
}


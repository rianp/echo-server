package echo.server;

import echo.SocketIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import echo.Console;

public class EchoServer implements Runnable {
  private final Console console;
  private final SocketIO socketIO;
  private final Socket clientSocket;

  public EchoServer(Console console, SocketIO socketIO, Socket clientSocket) {
    this.console = console;
    this.socketIO = socketIO;
    this.clientSocket = clientSocket;
  }

  @Override
  public void run() {
    try {
      String message;
      while ((message = socketIO.readMessage(clientSocket)) != null) {
        socketIO.sendMessage(clientSocket, message);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        clientSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

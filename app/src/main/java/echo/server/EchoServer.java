package echo.server;

import echo.SocketIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import echo.Console;

public class EchoServer {

  private final Console console;
  private final SocketIO socketIO;
  private final ServerSocket serverSocket;
  private Socket serverConnection;

  public EchoServer(Console console, ServerSocket serverSocket, SocketIO socketIO) {
    this.console = console;
    this.socketIO = socketIO;
    this.serverSocket = serverSocket;
  }

  public void start() throws IOException {
    String message;
    Socket serverConnection = this.acceptClientConnectionRequest();
    console.print("Connection established!");
    while ((message = socketIO.readMessage(serverConnection)) != null) {
      socketIO.sendMessage(serverConnection, message);
    }

  }

  public Socket acceptClientConnectionRequest() {
    Socket clientSocket;
    try {
      clientSocket = this.serverSocket.accept();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return clientSocket;
  }
}

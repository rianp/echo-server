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

  public EchoServer(Console console, ServerSocket serverSocket, SocketIO socketIO) {
    this.console = console;
    this.socketIO = socketIO;
    this.serverSocket = serverSocket;
  }

  public void start() throws IOException {
    Socket serverConnection = this.acceptClientConnectionRequest();
    console.print("Connection established!");
    String receivedMessage = socketIO.readMessage(serverConnection);
    console.print(receivedMessage);
    socketIO.sendMessage(serverConnection, receivedMessage);
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

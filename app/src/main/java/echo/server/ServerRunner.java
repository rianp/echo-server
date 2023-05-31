package echo.server;

import echo.Console;
import echo.SocketIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

  public static void main(final String[] args) throws IOException {
    Console console = new Console();
    SocketIO socketIO = new SocketIO();

    ServerSocket serverSocket = null;
    try {
      console.print("Welcome to the Echo Server!\nStarting Server...");
      serverSocket = new ServerSocket(49151);
      console.print("Echo Server started and waiting for clients... ");


      while (!Thread.currentThread().isInterrupted()) {
        Socket clientSocket = serverSocket.accept();
        console.print("Connection established!");
        EchoServer echoServer = new EchoServer(console, socketIO, clientSocket);

        new Thread(echoServer).start();

      }
    } finally {
      serverSocket.close();
    }
  }
}



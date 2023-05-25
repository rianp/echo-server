package echo.client;

import echo.Console;
import echo.SocketIO;
import java.io.IOException;
import java.net.Socket;

public class ClientRunner {

  public static void main(final String[] args) throws IOException {
    Console console = new Console();
    SocketIO socketIO = new SocketIO();

    console.print("Welcome to the Echo Client!\nRequesting Server Connection...");
    Socket clientSocket = new Socket("localhost", 49151);
    console.print("Connection established!");
    EchoClient echoClient = new EchoClient(console, clientSocket, socketIO);
    echoClient.start();
  }
}

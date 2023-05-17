package echo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class App {
  public static void main(final String[] args) throws IOException {
    Console.print("Welcome to the Echo Server!");
    Console.print("Starting Server...");
    ServerSocket serverSocket = EchoServer.startServerSocket(49151);
    Console.print("Echo Server started and waiting for clients... ");
    Socket client = EchoClient.createSocket(49151);
    Socket clientSocket = EchoServer.acceptClientSocket(serverSocket);
    Console.print("Connection established!");
    String message = Console.inputString("Enter message to echo please: ");
    EchoClient.sendClientMessageToServer(client, message);
    Scanner scanner = SocketIO.createSocketReader(clientSocket);
    SocketIO.readFromInputStream(scanner);
  }
}

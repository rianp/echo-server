/*
 *
 */

package echo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class App {
  public static void main(final String[] args) throws IOException {
    Console.print("Welcome to the Echo Server!");
    Console.print("Starting Server...");
    ServerSocket serverSocket = EchoServer.startServerSocket(49151);
    Console.print("Echo Server started and waiting for clients... ");
    Socket clientConnection = EchoClient.requestSocket(49151);
    Socket serverConnection = EchoServer.acceptClientConnectionRequest(serverSocket);
    Console.print("Connection established!");
    String message = Console.inputString("Enter message to echo please: ");
    SocketIO.sendMessage(clientConnection, message);
//    System.out.println("Socket is closed: " + serverConnection.isClosed());
    String receivedMessage = SocketIO.readMessage(serverConnection);
    SocketIO.sendMessage(serverConnection, receivedMessage);
    String serverMessage = SocketIO.readMessage(clientConnection);
    Console.print(serverMessage);
  }
}

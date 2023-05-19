/*
 *
 */

package echo.server;

import java.io.IOException;
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
    EchoClient.sendClientMessageToServer(clientConnection, message);
    String receivedMessage = SocketIO.readClientMessage(serverConnection);
  }
}

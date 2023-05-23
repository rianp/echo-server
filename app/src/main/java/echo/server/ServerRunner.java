/*
 *
 */

package echo.server;

import echo.client.EchoClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ServerRunner {
  EchoServer echoServer = new EchoServer();
  Console console = new Console();
  SocketIO socketIO = new SocketIO();
  public void main(final String[] args) throws IOException {
    console.print("Welcome to the Echo Server!\nStarting Server...");
    ServerSocket serverSocket = echoServer.startServerSocket(49151);
    console.print("Echo Server started and waiting for clients... ");

    while (true) {
      Socket serverConnection = echoServer.acceptClientConnectionRequest(serverSocket);
      console.print("Connection established!");
      String receivedMessage = socketIO.readMessage(serverConnection);
      console.print(receivedMessage);
      socketIO.sendMessage(serverConnection, receivedMessage);
    }
  }
}



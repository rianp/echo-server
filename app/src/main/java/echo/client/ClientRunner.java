/*
 *
 */

package echo.client;

import java.io.IOException;
import java.net.Socket;

class ClientRunner {
  EchoClient echoClient = new EchoClient();
  Console console = new Console();
  SocketIO socketIO = new SocketIO();
  public void main(final String[] args) throws IOException {

    while (true) {
      Socket clientConnection = echoClient.requestSocket(49151);
      console.print("Connection established!");
      String message = console.inputString("Enter message to echo please: ");
      socketIO.sendMessage(clientConnection, message);
      String echo = socketIO.readMessage(clientConnection);
      console.print(echo);
    }
  }
}

//    System.out.println("Socket is closed: " + serverConnection.isClosed());

// write a wrapper for server and for client

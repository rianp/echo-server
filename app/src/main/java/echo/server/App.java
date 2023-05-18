/*
 *
 */

package echo.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class App {
  public static void main(final String[] args) throws IOException {
    Console.print("Welcome to the Echo Server!");
    Console.print("Starting Server...");
    //create server socket and listening for connections
    ServerSocket serverSocket = EchoServer.startServerSocket(49151);
    Console.print("Echo Server started and waiting for clients... ");
    //create client socket
    Socket client = EchoClient.createSocket(49151);
    // accepting connection
    Socket clientSocket = EchoServer.acceptClientSocket(serverSocket);
    Console.print("after socket creation: " + clientSocket.isConnected());
    Console.print("Connection established!");
    String message = Console.inputString("Enter message to echo please: ");
    PrintWriter printWriter = EchoClient.sendClientMessageToServer(client, message);
    Console.print("after printwriter creation: " + clientSocket.isConnected());
    Scanner scanner = SocketIO.createSocketReader(clientSocket);
    Console.print("after scanner creation: " + clientSocket.isConnected());
    String input = SocketIO.readFromInputStream(scanner);
    Console.print("console message: "+ input);
    Console.print("after console message: " + clientSocket.isConnected());

    //send input back to client
    //Socket outputSocket = EchoClient.createSocket(49151);
    OutputStream outputStream = clientSocket.getOutputStream();
    BufferedOutputStream bufferedOutput = new BufferedOutputStream(outputStream);
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    byte[] converted = "OUTPUT".getBytes();
    Console.print("bytes: " + converted);
    Console.print("byteslen: " + converted.length);
    byteArray.write(converted);

    byte[] bytes = byteArray.toByteArray();
    Console.print("bytes array len: " + bytes.length);
    bufferedOutput.write(bytes);
    bufferedOutput
    bufferedOutput.flush();
    Console.print("after buffered message: " + clientSocket.isConnected());
//    printWriter.print("printWriter: " + input);
    //BufferedWriter bw = new BufferedWriter(printWriter);
    //bw.write(input);
//    EchoClient.sendClientMessageToServer(outputSocket, "OUTPUT");
  }
}

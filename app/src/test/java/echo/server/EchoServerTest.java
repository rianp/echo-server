package echo.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EchoServerTest {
  @Test
  void should_CreateServerSocket_When_ServerStarts() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket(0);
    Assertions.assertNotNull(serverSocket, "Should create server socket when the server starts");
    serverSocket.close();
  }

  @Test
  public void should_CreateServerSocket_With_SpecificPort() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket(9001);
    Assertions.assertEquals(serverSocket.getLocalPort(), 9001);
    serverSocket.close();
  }

  @Test
  void should_ThrowRuntimeException_When_ServerFailsToStart() {
    int port = 12345;

    EchoServer.startServerSocket(port);
    Assertions.assertThrows(RuntimeException.class, () -> EchoServer.startServerSocket(port));
  }

  @Test
  void should_AcceptClientSocket_When_ClientConnects() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket(9002);
    Thread serverThread = new Thread(() -> {EchoServer.acceptClientSocket(serverSocket);});
    serverThread.start();
    Socket clientSocket = new Socket("localhost", 9002);

    Assertions.assertTrue(clientSocket.isConnected(),
        "Client should be able to connect to the server");
    serverSocket.close();
  }
}
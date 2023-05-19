package echo.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EchoClientTest {

  private ServerSocket serverSocket;
  private int serverPort;


  @BeforeEach
  void setupServer() throws IOException {
    serverPort = 9001;
    serverSocket = new ServerSocket(serverPort);
  }

  @AfterEach
  void shutdownServer() throws IOException {
    serverSocket.close();
  }

  @Test
  void should_CreateClientSocket_When_ServerStarts() throws IOException {
    Socket clientSocket = new Socket("localhost", serverPort);
    clientSocket.close();
  }

  @Test
  void should_CreateClientSocket_With_SpecificPort() throws IOException {
    final int testPort = 9001;
    Socket testClientSocket = new Socket("localhost", testPort);
    testClientSocket.close();
  }

  @Test
  void should_ThrowRuntimeException_When_ServerFailsToStart() {
    int port = 12345;

    Assertions.assertThrows(RuntimeException.class, () -> EchoClient.createSocket(port));
  }

  @Test
  void should_SendClientMessageToServer_When_ClientHasInputMessage() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Socket socket = Mockito.mock(Socket.class);
    try {
      Mockito.when(socket.getOutputStream()).thenReturn(outputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String message = "Test message";

    EchoClient.sendClientMessageToServer(socket, message);

    String actualOutput = outputStream.toString();
    Assertions.assertEquals(message, actualOutput);
  }
}

package echo.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoClientTest {

  EchoClient echoClient = new EchoClient();
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

    Assertions.assertThrows(RuntimeException.class, () -> echoClient.requestSocket(port));
  }
}

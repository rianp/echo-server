package echo.server;

import java.io.IOException;
import java.net.ServerSocket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EchoServerTest {
  @Test
  void should_CreateServerSocket_When_ServerStarts() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket(0);
    Assertions.assertNotNull(serverSocket, "Should create server socket when the server starts");
  }

  @Test
  public void should_CreateServerSocket_When_SpecificPortIsUsedForSocket() throws IOException {
    final int testPort = 9001;
    ServerSocket testServerSocket =
        EchoServer.createSocket(testPort);
    Assertions.assertEquals(testServerSocket.getLocalPort(), testPort);
  }

  @Test
  void should_ThrowRuntimeException_When_ServerFailsToStart() {
    int port = 12345;

    EchoServer.startServer(port);
    Assertions.assertThrows(RuntimeException.class, () -> EchoServer.startServer(port));
  }
}

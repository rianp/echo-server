package echo.server;
import java.io.IOException;
import java.net.ServerSocket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EchoServerTest {
  @Test
  void Should_CreateServerSocket_When_ServerStarts() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket(0);
    assertNotNull(serverSocket, "Should create server socket when the server starts");
  }

  @Test
  public void Should_CreateServerSocket_When_SpecificPortIsUsedForSocket() throws IOException {
    final int testPort = 9001;
    ServerSocket testServerSocket =
        EchoServer.createSocket(testPort);
    assertEquals(testServerSocket.getLocalPort(), testPort);
  }

  @Test
  void Should_ThrowRuntimeException_When_ServerFailsToStart() {
    int port = 12345;

    EchoServer.startServer(port);
    assertThrows(RuntimeException.class, () -> EchoServer.startServer(port));
  }
}

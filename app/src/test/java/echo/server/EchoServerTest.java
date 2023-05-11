package echo.server;
import java.io.IOException;
import java.net.ServerSocket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EchoServerTest {
  @Test
  void testServerSocket() throws IOException {
    ServerSocket serverSocket = EchoServer.createSocket();
    assertNotNull(serverSocket, "Should create server socket when the server starts");
  }
}

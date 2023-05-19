package echo.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SocketIOTest {

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void should_ReadClientMessage_When_MessageIsSent() throws IOException {
    Socket clientSocket = Mockito.mock(Socket.class);
    InputStream inputStream = Mockito.mock(InputStream.class);

    String inputString = "Hello\n";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());

    Mockito.when(clientSocket.getInputStream()).thenReturn(inputStream);
    Mockito.when(inputStream.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt()))
        .thenAnswer(invocation -> byteArrayInputStream.read(
            (byte[]) invocation.getArgument(0),
            invocation.getArgument(1),
            invocation.getArgument(2)));

    String result = SocketIO.readClientMessage(clientSocket);

    Assertions.assertEquals("Hello", result);
  }
}

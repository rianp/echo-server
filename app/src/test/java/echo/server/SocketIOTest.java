package echo.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SocketIOTest {
  @Mock
  Socket clientSocket;
  @Mock
  InputStream inputStream;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void should_CreateASocketInput_When_SocketReaderIsCreated() throws IOException {
    Mockito.when(clientSocket.getInputStream()).thenReturn(inputStream);
    Assertions.assertNotNull(SocketIO.createSocketReader(clientSocket));
  }

  @Test
  public void should_ReadClientMessage_When_MessageIsSent() {
    String inputString = "Hello\n";
    Scanner input = new Scanner(new InputStreamReader(
        new ByteArrayInputStream(inputString.getBytes())));
    Assertions.assertEquals("Hello", SocketIO.readFromInputStream(input));
  }
}

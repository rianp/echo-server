package echo.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketIOTest {
  @Mock
  Socket clientSocket;
  @Mock
  InputStream inputStream;

  @Mock
  OutputStream outputStream;

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

//  @Test
//  public void should_CreateASocketOutput_When_SocketWriterIsCreated() throws IOException {
//    Mockito.when(clientSocket.getOutputStream()).thenReturn(outputStream);
//    SocketIO.createSocketOutput();
//
//
////    Assertions.assertNotNull(SocketIO.createSocketReader(clientSocket));
//
//  }
  }

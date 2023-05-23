package echo.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketIOTest {

  SocketIO socketIO = new SocketIO();
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

    String result = socketIO.readMessage(clientSocket);

    Assertions.assertEquals("Hello", result);
  }

//  @Test
//  void should_SendMessage_When_MessageIsGiven() throws IOException {
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    Socket socket = Mockito.mock(Socket.class);
//    try {
//      Mockito.when(socket.getOutputStream()).thenReturn(outputStream);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//    String message = "Test message";
//
//    socketIO.sendMessage(socket, message);
//
//    String actualOutput = outputStream.toString();
//    Assertions.assertEquals(message, actualOutput);
//  }

//  @Test
//  public void testSocketOutputGetsCreated() throws IOException {
//    Mockito.when(clientSocket.getOutputStream()).thenReturn(outputStream);
//    Assertions.assertNotNull(SocketIO.createSocketWriter(clientSocket));
//  }
//
//  @Test
//  public void testWriteClientStreamLine() {
//    String inputString = "World";
//    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    PrintWriter printWriter = new PrintWriter(
//        outContent, true);
//    SocketIO.writeToOutputStream(printWriter, inputString);
//    Assertions.assertEquals("World\n", outContent.toString());
//  }
}

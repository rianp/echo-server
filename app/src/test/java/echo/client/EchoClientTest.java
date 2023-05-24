package echo.client;

import echo.Console;
import echo.SocketIO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClientTest {
  SocketIO mockSocketIO = Mockito.mock(SocketIO.class);
  Socket mockSocket = Mockito.mock(Socket.class);
  Console mockConsole = Mockito.mock(Console.class);
  EchoClient echoClient = new EchoClient(mockConsole, mockSocket, mockSocketIO);

  @Test
  void should_printConnectionMessage() throws IOException {
    echoClient.start();

    Mockito.verify(mockConsole).inputString("Enter message to echo please: ");
  }

  @Test
  void should_sendMessage() throws IOException {
    String expectedInput = "test input";

    ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
    System.setIn(inputStream);

    echoClient.start();

    Mockito.verify(mockSocketIO).sendMessage(mockSocket, null);
  }

  @Test
  void should_receiveMessage() throws IOException {
    echoClient.start();

    Mockito.verify(mockSocketIO).readMessage(mockSocket);
  }

  @Test
  void should_printReceivedMessage() throws IOException {
    echoClient.start();

    Mockito.verify(mockConsole).print(null);
  }
}
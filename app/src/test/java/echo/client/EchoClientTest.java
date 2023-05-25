package echo.client;

import echo.Console;
import echo.SocketIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;

@DisplayName("when the echo client starts")
public class EchoClientTest {
  SocketIO mockSocketIO = mock(SocketIO.class);
  Socket mockSocket = mock(Socket.class);
  Console mockConsole = mock(Console.class);
  EchoClient echoClient = new EchoClient(mockConsole, mockSocket, mockSocketIO);

  @Test
  @DisplayName("should print a connection message")
  void should_printConnectionMessage() throws IOException {
    echoClient.start();

    verify(mockConsole).inputString("Enter message to echo please: ");
  }

  @Test
  @DisplayName("should be able to send a message")
  void should_sendMessage() throws IOException {
    String expectedInput = "test input";

    ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
    System.setIn(inputStream);

    echoClient.start();

    verify(mockSocketIO).sendMessage(mockSocket, null);
  }

  @Test
  @DisplayName("should be able to receive a message")
  void should_receiveMessage() throws IOException {
    echoClient.start();

    verify(mockSocketIO).readMessage(mockSocket);
  }

  @Test
  @DisplayName("should be able to print received message")
  void should_printReceivedMessage() throws IOException {
    echoClient.start();

    verify(mockConsole).print(null);
  }
}
package echo.client;

import echo.Console;
import echo.SocketIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
  @DisplayName("should be able to send a message")
  void should_sendMessage() throws IOException {
    String expectedInput = "test input";
    when(mockConsole.inputString("Enter a message to echo, or 'quit' to exit: "))
        .thenReturn(expectedInput)
        .thenReturn("quit");

    echoClient.start();

    verify(mockSocketIO).sendMessage(mockSocket, expectedInput);
  }

   @Test
  @DisplayName("should be able to print received message")
  void should_printReceivedMessage() throws IOException {
    String expectedInput = "test input";
    when(mockConsole.inputString("Enter a message to echo, or 'quit' to exit: "))
        .thenReturn(expectedInput)
        .thenReturn("quit");

    String echoedMessage = "echoed message";
    when(mockSocketIO.readMessage(mockSocket)).thenReturn(echoedMessage);

    echoClient.start();

    verify(mockConsole).print(echoedMessage);
  }

  @Test
  @DisplayName("should exit immediately if 'quit' is the first input")
  void shouldExitImmediately() throws IOException {
    when(mockConsole.inputString("Enter a message to echo, or 'quit' to exit: "))
        .thenReturn("quit");

    echoClient.start();

    verify(mockSocketIO, never()).sendMessage(any(Socket.class), anyString());
    verify(mockSocket, times(1)).close();
  }
}
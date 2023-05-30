package echo.server;

import echo.Console;
import echo.SocketIO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class EchoServerTest {
  SocketIO mockSocketIO = mock(SocketIO.class);
  ServerSocket mockServerSocket = mock(ServerSocket.class);
  Socket mockServerConnection = mock(Socket.class);
  Console mockConsole = mock(Console.class);
  EchoServer echoServer = new EchoServer(mockConsole, mockSocketIO, mockServerConnection);

  public void testSetUp() throws IOException {
    when(mockServerSocket.accept()).thenReturn(mockServerConnection);
  }

  @Test
  @DisplayName("should be able to receive a message when echo server starts")
  void should_receiveMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection))
        .thenReturn(null);

    echoServer.run();

    verify(mockSocketIO).readMessage(mockServerConnection);
  }

  @Test
  @DisplayName("should not send a message when echo server is not running")
  void should_notSendMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection)).thenReturn(null);

    echoServer.run();

    verify(mockSocketIO, never()).sendMessage(mockServerConnection, "quit");
  }

  @Test
  @DisplayName("should send a message when echo server is running")
  void should_sendMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection))
        .thenReturn("hello")
        .thenReturn(null);

    echoServer.run();

    verify(mockSocketIO).sendMessage(mockServerConnection, "hello");
  }
}
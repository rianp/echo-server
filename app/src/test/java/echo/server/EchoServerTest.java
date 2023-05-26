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
  EchoServer echoServer = new EchoServer(mockConsole, mockServerSocket, mockSocketIO);

  public void testSetUp() throws IOException {
    when(mockServerSocket.accept()).thenReturn(mockServerConnection);
  }

  @Test
  @DisplayName("should print a connection message when echo server starts")
  void should_printConnectionMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection))
        .thenReturn("quit")
        .thenReturn(null);

    echoServer.start();

    verify(mockConsole).print("Connection established!");
  }

  @Test
  @DisplayName("should be able to receive a message when echo server starts")
  void should_receiveMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection))
        .thenReturn(null);

    echoServer.start();

    verify(mockSocketIO).readMessage(mockServerConnection);
  }

  @Test
  @DisplayName("should not send a message when echo server is not running")
  void should_notSendMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection)).thenReturn(null);

    echoServer.start();

    verify(mockSocketIO, never()).sendMessage(mockServerConnection, "quit");
  }

  @Test
  @DisplayName("should send a message when echo server is running")
  void should_sendMessage() throws IOException {
    testSetUp();
    when(mockSocketIO.readMessage(mockServerConnection))
        .thenReturn("hello")
        .thenReturn(null);

    echoServer.start();

    verify(mockSocketIO).sendMessage(mockServerConnection, "hello");
  }
}
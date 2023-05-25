package echo.server;

import echo.Console;
import echo.SocketIO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EchoServerTest {
  SocketIO mockSocketIO = mock(SocketIO.class);
  ServerSocket mockServerSocket = mock(ServerSocket.class);
  Console mockConsole = mock(Console.class);
  EchoServer echoServer = new EchoServer(mockConsole, mockServerSocket, mockSocketIO);

  public void testSetUp() throws IOException {
    when(mockServerSocket.accept()).thenReturn(new Socket());
  }

  @Test
  @DisplayName("should return a socket when a client connection is made")
  void should_ReturnSocket_When_ClientConnectionIsMade() throws IOException {
    testSetUp();
    Socket testClientSocket = echoServer.acceptClientConnectionRequest();

    assertEquals(testClientSocket.getClass(), Socket.class);

    mockServerSocket.close();
  }

  @Test
  @DisplayName("should accept client connection when echo server starts")
  void should_AcceptClientConnection() throws IOException {
    EchoServer echoSpy = spy(echoServer);
    echoSpy.start();

    verify(echoSpy).acceptClientConnectionRequest();
  }

  @Test
  @DisplayName("should print a connection message when echo server starts")
  void should_printConnectionMessage() throws IOException {
    echoServer.start();

    verify(mockConsole).print("Connection established!");
  }

  @Test
  @DisplayName("should be able to receive a message when echo server starts")
  void should_receiveMessage() throws IOException {
    echoServer.start();

    verify(mockSocketIO).readMessage(null);
  }

  @Test
  @DisplayName("should be able to print a received message when echo server starts")
  void should_printReceivedMessage() throws IOException {
    echoServer.start();

    verify(mockConsole).print(null);
  }

  @Test
  @DisplayName("should be able to send a message when echo server starts")
  void should_sendMessage() throws IOException {
    echoServer.start();

    verify(mockSocketIO).sendMessage(null, null);
  }
}
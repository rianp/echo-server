package echo.server;

import echo.Console;
import echo.SocketIO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerTest {
  SocketIO mockSocketIO = Mockito.mock(SocketIO.class);
  ServerSocket mockServerSocket = Mockito.mock(ServerSocket.class);
  Console mockConsole = Mockito.mock(Console.class);
  EchoServer echoServer = new EchoServer(mockConsole, mockServerSocket, mockSocketIO);

  public void testSetUp() throws IOException {
    Mockito.when(mockServerSocket.accept()).thenReturn(new Socket());
  }

  @Test
  void should_return_socket_when_acceptClientConnection_is_called() throws IOException {
    testSetUp();
    Socket testClientSocket = echoServer.acceptClientConnectionRequest();

    Assertions.assertEquals(testClientSocket.getClass(), Socket.class);

    mockServerSocket.close();
  }

  // describe start()

  @Test
  void should_AcceptClientConnection() throws IOException {
    EchoServer echoSpy = Mockito.spy(echoServer);
    echoSpy.start();

    Mockito.verify(echoSpy).acceptClientConnectionRequest();
  }

  @Test
  void should_printConnectionMessage() throws IOException {
    echoServer.start();

    Mockito.verify(mockConsole).print("Connection established!");
  }

  @Test
  void should_receiveMessage() throws IOException {
    echoServer.start();

    Mockito.verify(mockSocketIO).readMessage(null);
  }

  @Test
  void should_printReceivedMessage() throws IOException {
    echoServer.start();

    Mockito.verify(mockConsole).print(null);
  }

  @Test
  void should_sendMessage() throws IOException {
    echoServer.start();

    Mockito.verify(mockSocketIO).sendMessage(null, null);
  }
}
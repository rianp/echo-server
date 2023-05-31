package echo.server;

import echo.Console;
import echo.SocketIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EchoServerTest {
  SocketIO mockSocketIO = mock(SocketIO.class);
  ServerSocket mockServerSocket = mock(ServerSocket.class);
  Socket mockServerConnection = mock(Socket.class);
  Console mockConsole = mock(Console.class);
  EchoServer echoServer = new EchoServer(mockConsole, mockSocketIO, mockServerConnection);

  @BeforeEach
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

  @Test
  @DisplayName("should return messages to multiple clients when more than one client sends a message")
  public void should_sendMultipleClientsMessages() throws IOException, InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // Start the server in a separate thread
    executorService.execute(() -> {
      try {
        ServerRunner.main(null);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    // Wait for the server to start
    Thread.sleep(1000);

    int numClients = 5;
    for (int i = 0; i < numClients; i++) {
      // Start each client in a separate thread
      int finalI = i;
      executorService.execute(() -> {
        try (Socket socket = new Socket("localhost", 49151);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

          String message = "Hello from client " + finalI;
          out.println(message);
          String response = in.readLine();
          assertEquals(message, response);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }

    // Wait for all clients to finish
    executorService.shutdown();
    Thread.sleep(5000);

    executorService.shutdownNow();
  }
}
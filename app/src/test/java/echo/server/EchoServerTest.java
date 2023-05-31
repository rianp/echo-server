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
import java.util.concurrent.TimeUnit;

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
  @DisplayName("should connect to multiple clients when more than one client requests connection")
  public void testMultipleClientConnections() throws IOException, InterruptedException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Create a server instance in a separate thread
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      try {
        ServerRunner.main(null);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    Thread.sleep(1000);

    // Create multiple client connections
    int numClients = 5;
    for (int i = 0; i < numClients; i++) {
      Socket clientSocket = new Socket("localhost", 49151);
      clientSocket.close();
    }

    // Wait for the server to handle all client connections
    Thread.sleep(1000);

    String expectedOutput = "Welcome to the Echo Server!\n" +
        "Starting Server...\n" +
        "Echo Server started and waiting for clients... \n" +
        "Connection established!\n".repeat(numClients);
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  @DisplayName("should return messages to multiple clients when more than one client sends a message")
  public void testMultipleClientsMessaging() throws IOException, InterruptedException {
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
      executorService.execute(() -> {
        try (Socket socket = new Socket("localhost", 49151);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

          String message = "quit";
          out.println(message);
          String response = in.readLine();
          assertEquals(message, response);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }

    // Stop the server
    executorService.shutdownNow();
  }
}
package echo.server;

import org.junit.jupiter.api.Test;
import java.io.*;
import org.junit.jupiter.api.Assertions;

public class AppTest {

  @Test
  public void should_ReceiveUserInput_When_ServerStarts() throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    String userInput = "Hello, World!\n";
    InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(inputStream);

    App.main(new String[0]);

    String expectedOutput = "Welcome to the Echo Server!\n" +
                            "Starting Server...\n" +
                            "Echo Server started and waiting for clients... \n" +
                            "Connection established!\n" +
                            "Enter message to echo please: \n";
    Assertions.assertEquals(expectedOutput, outputStream.toString());
  }
}
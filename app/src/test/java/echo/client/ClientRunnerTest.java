package echo.client;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ClientRunnerTest {

  @Test
  public void should_ReceiveUserInput_When_ServerStarts() throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    String userInput = "Hello, World!\n";
    InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(inputStream);

    ClientRunner.main(new String[0]);

    String expectedOutput = """
        Welcome to the Echo Server!
        Starting Server...
        Echo Server started and waiting for clients...\s
        Connection established!
        Enter message to echo please:\s
        """;
    Assertions.assertEquals(expectedOutput, outputStream.toString());
  }
}
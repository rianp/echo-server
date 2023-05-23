package echo.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ConsoleTest {
  Console console = new Console();
  private InputStream originalSystemIn;

  @BeforeEach
  public void setUp() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    originalSystemIn = System.in;
  }

  @AfterEach
  public void tearDown() {
    System.setIn(originalSystemIn);
    System.setOut(System.out);
  }

  @Test
  public void should_PrintCorrectOutput_When_PrintingString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    console.print("Print");

    String output = outputStream.toString().trim();
    Assertions.assertEquals("Print", output);
  }


  @Test
  public void should_ReturnInputString_When_ProvidingInput() throws IOException {
    String expectedInput = "test input";

    ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
    System.setIn(inputStream);

    String input = console.inputString("Enter input: ");

    Assertions.assertEquals(expectedInput, input);
  }
}

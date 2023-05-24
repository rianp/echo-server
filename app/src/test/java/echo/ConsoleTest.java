package echo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ConsoleTest {
  Console console = new Console();

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

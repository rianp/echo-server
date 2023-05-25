package echo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {
  Console console = new Console();

  @Test
  @DisplayName("should output a string that matches the provided input")
  public void should_PrintCorrectOutput_When_PrintingString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    console.print("Print");

    String output = outputStream.toString().trim();
    assertEquals("Print", output);
  }


  @Test
  @DisplayName("should return a string that matches the string entered by the user")
  public void should_ReturnInputString_When_ProvidingInput() throws IOException {
    String expectedInput = "test input";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(expectedInput.getBytes());
    System.setIn(inputStream);

    String input = console.inputString("Enter input: ");

    assertEquals(expectedInput, input);
  }
}

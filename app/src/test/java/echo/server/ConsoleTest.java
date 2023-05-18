package echo.server;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ConsoleTest {

  @Test
  public void should_PrintCorrectOutput_When_PrintingString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    Console.print("Print");

    String output = outputStream.toString().trim();
    Assertions.assertEquals("Print", output);
  }

}

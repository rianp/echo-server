package echo.server;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTest {

  @Test
  public void Should_PrintCorrectOutput_When_PrintingString() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    Console.print("Print");

    String output = outputStream.toString().trim();
    assertEquals("Print", output);
  }

}

/*
 *
 */

package echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

  public static void print(String output) {
    System.out.println(output);
  }

  protected static String inputString(String prompt) throws IOException {
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    print(prompt);
    return userInput.readLine();
  }
}

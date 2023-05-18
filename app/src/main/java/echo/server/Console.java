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

  protected static String inputString(String string) {
    print(string);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String inputString = "";

    try {
      inputString = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return inputString;
  }
}

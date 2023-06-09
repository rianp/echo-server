package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

  public void print(String output) {
    System.out.println(output);
  }

  public String inputString(String prompt) throws IOException {
    print(prompt);
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    return userInput.readLine();
  }
}

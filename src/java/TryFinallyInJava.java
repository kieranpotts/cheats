import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TryFinallyInJava {
  public static void main(String[] args) throws IOException {

    /*
    `try` blocks MUST be paired with either a `catch` or `finally` block, or both.
    The `finally` block is executed regardless of whether an exception is
    thrown or not. This is useful for freeing up resources, like closing
    a file or network connection.
    */

    int num = 0;
    BufferedReader reader =  null;

    try {
      reader = new BufferedReader(new InputStreamReader(System.in));
      num = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      reader.close();
    }

    System.out.println(num);

    /*
    Alternative syntax for closing resources in Java 7:
    This is called a `try-with-resources` statement. The resource MUST
    implement the `AutoCloseable` interface. The resource is closed
    automatically when the try block is exited.
    */

    int num2 = 0;
    try (BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in))) {
      num2 = Integer.parseInt(reader2.readLine());
    }

    System.out.println(num2);
  }
}

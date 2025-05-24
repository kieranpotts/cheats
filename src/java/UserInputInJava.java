import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputInJava {
  public static void main(String[] args) {
    // new ReadExample().test();
    // new BufferedReaderExample().test();
    new ScannerExample().test();
  }
}

class ReadExample {
  public void test() {
    System.out.println("Enter a number:");

    try {
      /* Captures ASCII value of first inputted character only: */
      int num = System.in.read();
      System.out.println("ASCII value of first character: " + num);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class BufferedReaderExample {
  public void test() {
    System.out.println("Enter a number:");

    try {

      /*
      `BufferedReader` is a built-in class for working with IO.
      It is a convenient way of reading lines of text from the console.
      */

      InputStreamReader input = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(input);

      /* Convert string value of input to integer: */
      int num = Integer.parseInt(reader.readLine());
      System.out.println(num);

      /*
      `BufferReader` can also capture input from files and networks
      and other sources of input to a program. In this example
      `InputStreamReader` is used to read input from the console.

      `BufferedReader` instances are a type of "resource". Whenever you
      open a resource – like a file, network connection, or database –
      it is your responsibility to close it again, when you are done
      with it. This is best practice to free-up resources for
      optimization.
      */

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class ScannerExample {
  public void test() {
    System.out.println("Enter a number:");

    /*
    There is a newer way in Java (since v1.5) to read input from the
    console. It is a utility class called `Scanner`. The `System.in`
    argument refers to input captured from the console rather than,
    say, a file or network connection.
    */

    Scanner scanner = new Scanner(System.in);
    int num2 = scanner.nextInt();
    scanner.close();

    System.out.println(num2);
  }
}

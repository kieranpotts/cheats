public class LoopsInJava {
  public static void main(String[] args) {
    /* Endless loop! */
    // while (true)
    // {
    //   System.out.println("Hello");
    // }

    /* While loop */

    /* Prints "Hello" 5 times. */
    int i = 1;
    while (i <= 5) {
      System.out.println("Hello " + i);
      i++;
    }
    System.out.println("Bye " + i); // "Bye 6"

    /* Do-while loop */

    /* Prints "Hello" 5 times. */
    i = 5;
    do {
      System.out.println("Hello " + i);
      i--;
    } while (i > 0);
    System.out.println("Bye " + i); // "Bye 0"

    /* For loop */

    /* Prints "Hello" 5 times. */
    for (int j = 1; j <= 5; j++) {
      System.out.println("Hello " + j);
    }
    /* No access to j here, it is scoped to the `for` block. */
    System.out.println("Bye"); // "Bye"

    /* Prints "Hello" 5 times. */
    int j = 1;
    for (; j <= 5; j++) {
      System.out.println("Hello " + j);
    }
    /* The j variable is now available. */
    System.out.println("Bye " + j); // "Bye 6"

    /* Enhanced for loop (aka `for/each` loop) */
    int arr[] = {3,7,4,2};
    for (int value: arr) {
      System.out.println(value);
    }
  }
}

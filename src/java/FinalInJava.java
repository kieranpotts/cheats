public class FinalInJava {
  /*
  The final keyword can be used on classes, methods, and variables (fields).
  */

  public static void main(String[] args) {

    /*
    Always use the final keyword for values that are
    intended to be constants.
    */

    @SuppressWarnings("unused")
    final int x = 10;

    /* Compile error: cannot assign a value to final variable x */
    // x = 20;

    @SuppressWarnings("unused")
    final int y;
    y = 20;

    /* Compile error: cannot assign a value to final variable y */
    // y = 30;
  }
}

/*
The final keyword can be used to prevent class inheritance.
*/
final class Calculator {

  /*
  Alternatively, the class can be kept open for extension, but
  the final keyword can be used to prevent individual method overriding
  within it.
  */

  public final int add(int x, int y) {
    return x + y;
  }
}

/* Calculator cannot be extended because it is final. */
// class AdvancedCalculator extends Calculator
// {
//    //  …
// }

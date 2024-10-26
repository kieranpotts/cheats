public class ArithmeticOperatorsInJava {
  public static void main(String[] args) {
    int num1 = 7;
    int num2 = 5;

    /*
    ----------------------------------------------------------------------------
    ADDING
    ----------------------------------------------------------------------------
    */

    int add = num1 + num2;
    System.out.println(add); // 12

    add = add + 1;

    /* Shortcut operation and reassignment. */
    add += 1;

    /* Shortcut for adding 1 and reassignment (post-increment). */
    add++;

    System.out.println(add); // 15

    /*
    Pre-increment and pre-decrement operators are also available.
    In post-increments, the value is read (and therefore assigned) before
    the increment/decrement operation.
    */

    int preAdd = ++add;
    System.out.println(preAdd); // 16
    System.out.println(add); // 16

    int postAdd = add++;
    System.out.println(postAdd); // 16
    System.out.println(add); // 17

    /*
    ----------------------------------------------------------------------------
    SUBTRACTING
    ----------------------------------------------------------------------------
    */

    int subtract = num1 - num2;
    System.out.println(subtract); // 2

    /*
    ----------------------------------------------------------------------------
    MULTIPLYING
    ----------------------------------------------------------------------------
    */

    int multiply = num1 * num2;
    System.out.println(multiply); // 35

    /*
    ----------------------------------------------------------------------------
    DIVIDING
    ----------------------------------------------------------------------------
    */

    int divide = num1 / num2;
    System.out.println(divide); // 1

    /*
    ----------------------------------------------------------------------------
    MODULUS
    ----------------------------------------------------------------------------
    */

    /*
    The modulus operator (`%`) returns the remainder after the first operand is
    *evenly* divided by the second operand. In this case, 7 / 5 = 1, with
    a remainder of 2.
    */

    int modulus = num1 % num2;
    System.out.println(modulus); // 2

    /* 7 divides evenly into 3 twice (3x2=6) with a reminder of 1 (7-6=1). */
    System.out.println(7 % 3); // 2

    /*
    The modulus operator is often used to determine whether a number is
    even or odd. If x % 2 is 0, then x is even, otherwise it is odd.
    */

    System.out.println(6 % 2); // 0
    System.out.println(7 % 2); // 1

    /*
    If the return value is 0, it means the first operand is evenly
    divisible by the second operand.
    */

    System.out.println(6 % 3); // 0

    /*
    And you can use 10, 100, 1000, ectc.… as the second operand to extract the
    last one, two, three, etc.… digits from a number.
    */

    System.out.println(12345 % 10); // 5
    System.out.println(12345 % 100); // 45
    System.out.println(12345 % 1000); // 345
  }
}

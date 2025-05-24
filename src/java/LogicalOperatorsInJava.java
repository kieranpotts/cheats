public class LogicalOperatorsInJava {
  public static void main(String[] args) {
    int x = 7;
    int y = 5;
    int a = 5;
    int b = 9;

    /* && Short-circuit AND */

    boolean result1 = x > y && a > b;
    System.out.println(result1); // false

    /* || Short-circuit OR */

    boolean result2 = x > y || a > b;
    System.out.println(result2); // true

    /* ! NOT */

    boolean result3 = x < y;
    System.out.println(!result3); // true

    boolean result4 = !(x < y);
    System.out.println(result4); // true
  }
}

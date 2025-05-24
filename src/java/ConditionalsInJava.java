public class ConditionalsInJava {
  public static void main(String[] args) {
    int x = 8;
    int y = 7;

    /* if / else-if / else */

    if (x > y) {
      System.out.println(x);
      System.out.println("x is greater than y");
    } else if (x < y) {
      System.out.println(y);
      System.out.println("x is less than y");
    }
    /* Brackets are optional where there is just one statement. */
    else
       System.out.println("x is equal to y");

    /* Ternary operator `?:`. */

    int n = 4;
    int result = 0;

    if (n % 2 == 0) // is even
      result = 10;
    else
      result = 20;

    System.out.println(result); // 10

    /* Alternative with ternary operator `?:`. */

    result = n % 2 == 0 ? 10 : 20;
    System.out.println(result); // 10

    /* Switch */

    /*
    Before Java 5, `switch` statements could only be used with
    integers; strings were not supported.
    */

    n = 4;

    switch (n) {
      case 1, 2, 3:
      case 4, 5:
        System.out.println("Weekday");
        break;
      case 6:
        System.out.println("Saturday");
        break;
      default:
        System.out.println("Sunday");
    }

    /*
    From Java 12, the arrow notation was introduced, as an alternative
    to the colon. With this syntax, the break is implicit.
    */

    String day = "Monday";

    switch (day) {
      case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> System.out.println("Weekday");
      case "Saturday" -> System.out.println("Saturday");
      default -> {
        String sunday = "Sunday";
        System.out.println(sunday);
      }
    }

    /*
    From Java 13, switch statements can be used as expressions, and
    therefore can be used to assign values to variables. Previously you
    had to do multiple assignments within the cases, as below:
    */

    day = "Sunday";
    String output = "";

    switch (day) {
      case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> output = "Weekday";
      case "Saturday" -> output = "Saturday";
      default -> output = "Sunday";
    }

    System.out.println(output);

    /*
    From Java 13, you can use the arrow notation to point to the value
    you want to return. (Note the semicolon on the end of the switch block.
    This is required because the switch statement is now being used within an
    expression.)
    */

    output = switch (day) {
      case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
      case "Saturday" -> "Saturday";
      default -> "Sunday";
    };

    System.out.println(output);

    /*
    Alternatively you can use the colon notation, but in this case the value
    must be explicitly returned using the `yield` keyword.
    */

    output = switch (day) {
      case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday": yield "Weekday";
      case "Saturday": yield "Saturday";
      default: yield "Sunday";
    };

    System.out.println(output);
  }
}

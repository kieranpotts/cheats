/*
Java 8 introduced lambda expressions, which provide a shorthand for
creating anonymous classes that implement functional interfaces.
In fact, lambda expressions can *only* be used with functional interfaces!
*/

public class LambdaExpressionsInJava {
  public static void main(String[] args) {

    /*
    This is an example of implementing a functional interface
    as an inner class.
    */

    A a = new A() {
      public void show() {
        System.out.println("Inner");
      }
    };
    a.show(); // "Inner"

    /*
    A lambda expression is a shorthand for creating an anonymous class
    that implements a functional interface. It's just a more compact
    way of writing the above code.
    */

    A a2 = () -> System.out.println("Lambda");
    a2.show(); // "Lambda"

    /*
    If the method is composed of multiple statements, curly braces must
    enclose the body of the method.
    */

    A a3 = () -> {
      System.out.println("Multiple");
      System.out.println("Statements");
    };
    a3.show(); // "Multiple\nStatements"

    /*
    Lambda expressions can also take arguments. The method does not
    need to specify the types of the arguments, as the interface
    definition already does that. If there is just one argument, you
    can omit the parentheses around the lambda expression.
    */

    B b = s -> System.out.println(s);
    b.show("Hello"); // "Hello"

    /*
    If the functional method returns a value, the lambda expression
    must return a value of the correct type.
    */

    C c = (i, j) -> {
      return i + j;
    };
    int result = c.add(1,2); // 3
    System.out.println(result);

    /* Or, even more concisely: */
    C c2 = (i, j) -> i + j;
    int result2 = c2.add(1,2); // 3
    System.out.println(result2);
  }
}

@FunctionalInterface
interface A {
  void show();
}

@FunctionalInterface
interface C {
  int add(int i, int j);
}

@FunctionalInterface
interface B {
  void show(String s);
}

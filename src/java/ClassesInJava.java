public class ClassesInJava {
  public static void main(String[] args) {
    Calculator calc = new Calculator();
    int result = calc.add(1, 2);
    System.out.println(result); // 3

    result = calc.add(1, 2, 3);
    System.out.println(result); // 6

    /* Access static methods via the class name. */
    result = Calculator.multiply(2, 3);
    System.out.println(result); // 6

    /* Access instance variables via the object. */
    calc.prop = 5;
    System.out.println(calc.prop); // 5

    /* Access static variables via the class name. */
    Calculator.staticProp = 5;
    System.out.println(Calculator.staticProp); // 5
  }
}

class Calculator {
  /* Instance variable. */
  int prop;

  /* Can also have static variables, which are shared across all instances. */
  static int staticProp;

  /*
  Static block to initialize static variables. The static block is
  called once only, when the class is loaded into memory, not when
  you subsequently create instances of the class.

  If you want to "load a class" without ever creating an instance of it,
  you need to use `Class.forName("ClassName")`. You will typically
  call this from another class. This is necessary if you want to
  initialize static variables in a class, but you don't want to create
  an instance of the class. This feature is used rarely.
  */

  static {
    staticProp = 1;
  }

  /* Constructor. */
  public Calculator() {
    /* Do instance initialization here. */
    prop = 1;

    /*
    You can initialize static variables here too, but it's not
    common because it doesn't make sense logically, better to
    initialize them in a static block – see above.
    */

    staticProp = 1;
  }

  public int add(int n1, int n2) {
    return n1 + n2;
  }

  /*
  Method overloading - can have multiple methods with the same name, as
  long as they have different parameters. Return types may vary.
  */

  public int add(int n1, int n2, int n3) {
    return n1 + n2 + n3;
  }

  /* Static methods. */
  public static int multiply(int n1, int n2) {

    /*
    You cannot access instance variables from static methods.
    To do this you'd need to pass in an instance of the class,
    and access the instance variables via that instance.
    */

    // prop = 5;

    return n1 * n2;
  }
}

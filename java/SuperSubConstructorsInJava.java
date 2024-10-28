public class SuperSubConstructorsInJava {
  public static void main(String a[]) {

    /*
    prints:
    "Super" (super() calls the first constructor of Super)
    "Sub-5" (output from the second constructor of Sub, with an int argument)
    */

    new Sub(5);
  }
}

class Super { // extends Object
  public Super() {

    /*
    Every class in Java extends from Object, even if not explicit.
    Therefore `super()` is always called implicitly.
    */

    // super()
    System.out.println("Super");
  }

  public Super(int n) {
    System.out.println("Super-" + n);
  }
}

class Sub extends Super {
  public Sub() {
    System.out.println("Sub");
  }

  public Sub(int n) {

    /*
    If we called `this()` here, it would call the first constructor of Sub,
    which would then implicitly call the first constructor of Super.

    ----
    this();
    ----

    `super()` is automatically called by the compiler, even if it's not
    explicitly written.

    ----
    super();
    ----

    We could change the behavior of the `super()` call by passing an argument to
    it. This would then call the second constructor of `Super`. This would change
    the output to "Super-5" and "Sub-5".

    ----
    super(n);
    ----
    */

    System.out.println("Sub-" + n);
  }
}

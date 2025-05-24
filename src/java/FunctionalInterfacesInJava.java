/*
A functional interface is an interface with exactly one abstract method.

The `@FunctionalInterface` annotation is optional, but it is a good practice to
use it. It is used to ensure that the interface has only one abstract method.
If you try to add another abstract method, the compiler will raise an error.
*/

public class FunctionalInterfacesInJava {
  public static void main(String[] args) {
    B b = new B();
    b.show(); // "B"

    /* Or you can create an inner class that implements A. */
    A a = new A() {
      public void show() {
        System.out.println("Inner");
      }
    };
    a.show(); // "Inner"
  }
}

@FunctionalInterface
interface A {
  void show();
}

class B implements A {
  public void show() {
    System.out.println("B");
  }
}

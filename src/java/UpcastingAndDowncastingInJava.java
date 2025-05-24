public class UpcastingAndDowncastingInJava {
  public static void main(String[] args) {

    /* This would lose data: */
    // double d = 4.5;
    // int i = d; // Type mismatch: cannot convert from double to int.

    /*
    But if you are okay with losing data, you can use typecasting,
    which is the process of converting a value of one type to another type.
    */

    double d = 4.5;
    int i = (int) d;
    System.out.println(i); // 4

    /*
    Upcasting is where we cast an object to a parent class.
    In this case, we create an instance of `B` but we cast it to type `A`,
    which is a super class of `B`.
    */

    // A a = (A) new B();
    // a.show1(); // "A"

    /* But this just works, you don't actually need to cast it: */
    A a = new B();
    a.show1(); // "A"

    /*
    Downcasting is where we cast an object to a child class.
    We do this when we want to access the methods or fields of the child class.
    */

    // a.show2(); // Error: The method show2() is undefined for the type A

    /* To fix the error, we need to downcast the object to the child class. */
    B b = (B) a;
    b.show2(); // "B"
  }
}

class A {
  public void show1() {
    System.out.println("A");
  }
}

class B extends A {
  public void show2() {
    System.out.println("B");
  }
}

/*
In Java, polymorphism can be achieved at compile time ("overloading") and at
runtime ("overriding").

Overloading (compile-time polymorphism)
=======================================

Overloading is when two or more methods in the same class have the same name
but different parameters.

----
add(int, int)
add(int, int, int)
----

Overriding (runtime polymorphism)
=================================

Overriding is achieved by using something called *dynamic method dispatch*. This
is when a call to an overridden method is resolved at runtime, rather than
compile time. Which methods get called are determined by the type of object (or
implementation), which is _assigned_ to variables at runtime, not the type of
the reference variable, which is _declared_ at compile time.
*/


public class PolymorphismInJava {
  public static void main(String[] args) {

    /* The type of `a` `A`, but the object/implementation is of type `B`. */
    A a = new B();
    a.show(); // A

    /*
    The type of `a` is `A`, as defined previously, but now the implementation
    is of type `C`. This time, the `show` method of `C` is called, as it
    overrides the method of the same name in `A`.
    */

    a = new C();
    a.show(); // C
  }
}

class A {
  public void show() {
    System.out.println("A");
  }
}

class B extends A {}

class C extends A {
  public void show() {
    System.out.println("C");
  }
}

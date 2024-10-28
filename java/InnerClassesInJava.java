public class InnerClassesInJava {
  public static void main(String[] args) {
    A a = new A();
    a.show(); // "Hello"

    /*
    B belongs to A.
    The type is A.B, and instances are created via an instance of A.
    */

    A.B b = a.new B();
    b.display(); // "World"

    /*
    Static inner classes are not bound to an instance of the outer class.
    So the access syntax is slightly different.
    */

    A.C c = new A.C();
    c.print(); // "Print"

    /*
    You can even have anonymous inner classes. This is useful when you
    know a subclass will be used only once, and you don't want to define
    a separate class for it. The syntax is unusual; you define the
    design of the subclass (technically, an inner class) after its
    instantiation!
    */

    A a2 = new A() {
        public void show() {
            System.out.println("Hello from the anonymous class");
        }
    };
    a2.show(); /* "Hello from the anonymous class" */

    /*
    NOTE: You can use anonymous inner classes to implement abstract
    classes and then immediately instantiate them. This is a common
    pattern.
    */
  }
}

class A {
  int age;

  public void show() {
    System.out.println("Hello");
  }

  /* Inner classes are classes defined within another class. */

  /* Inner class B */
  class B {
    public void display() {
      System.out.println("World");
    }
  }

  static class C {

    /*
    Static inner class C

    NOTE: Only inner classes can be static, it is not possible to
    create an outer static class.
    */

    public void print() {
      System.out.println("Print");
    }
  }
}

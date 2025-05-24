public class MethodOverridingInJava {
  public static void main(String[] args) {
    A a = new A();
    a.show(); // "A"

    B b = new B();
    b.show(); // "B"
  }
}

class A {
  public void show() {
    System.out.println("A");
  }
}

class B extends A {
  /* The @Override annotation is optional but RECOMMENDED. */
  @Override
  public void show() {
    System.out.println("B");
  }
}

public class AnonymousObjectsInJava {
  public static void main(String[] args) {
    /* Anonymous object (it has no reference). */
    new A().show();
  }
}

class A {
  public void show() {
    System.out.println("Hello");
  }
}

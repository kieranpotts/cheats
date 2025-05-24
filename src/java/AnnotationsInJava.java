/*
In Java, annotations are used to provide metadata about the program to the
compiler or runtime. Annotations don't change how the program works, they
just provide extra information about the program to the environment that
will run it.

Java has quite a few built-in annotations, and some such as `@Override` and
`@Deprecated` are used quite frequently. You can also create your own
annotations, and most Java application frameworks use annotations extensively.
For example, a great deal of the Spring framework is driven by annotations.
So more likely you will use framework-specific annotations rather than
Java ones or your own ones.
*/

public class AnnotationsInJava {
  public static void main(String[] args) {
    B b = new B();
    b.show(); // "B"

    /*
    The expected output here is "B", but it is "A" because there is a
    bug - a mistake in the logic. The method name is misspelled.
    */

    b.thisIsAReallyLongMethodName(); // "A"
  }
}

class A {
  public void show() {
    System.out.println("A");
  }

  public void thisIsAReallyLongMethodName() {
    System.out.println("A");
  }
}

class B extends A {

  /*
  The following is an example of method overriding.
  This works perfectly fine without the `@Override` annotation,
  because the method signature is *exactly* the same as the corresponding
  method in the parent class.
  */

  public void show() {
    System.out.println("B");
  }

  /*
  This is intended to override the method of the same name in the parent class,
  but actually it does not because there is a hard-to-spot typo in the
  method name (method names are case sensitive).

  The solution is to show your intention to the compiler by using the
  `@Override` annotation. The compiler will then raise an error if the
  method does not actually override anything inherited from the parent class.
  Thus the bug is caught at compile time rather than runtime.
  */

  @Override
  public void thisIsaReallyLongMethodName() {
    System.out.println("B");
  }
}

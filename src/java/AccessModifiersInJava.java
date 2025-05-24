/*
In Java, access modifiers can be applied to classes and their members (ie. their
attributes, methods, and constructors).

For classes, there's only one access modifier: `public`. By default, without a
modifier, a class is accessible only within its package. Adding the `public`
modifier makes the class accessible from absolutely everywhere.

Class members have three access modifiers: `private`, `protected`, and `public`.
The default behavior is that members (without modifiers) are quasi-public, in
the sense they are accessible from anywhere within the same package (which is
the same default behavior as classes themselves).

Adding one of the three access modifiers changes the scope of access to the
member, as follows:

* `private` members can be accessed only from within the same class.

* `protected` members can be accessed from anywhere within the same package
    (the default behavior), and in addition they can be accessed from any
    subclass, even subclasses defined in other packages.

* `public` members can be accessed from absolutely everywhere.

THe following table summarizes the access levels for class members (they default
and `public` options are also relevant for classes themselves):

| Accessible from:                  | `public` | `protected` | Default * | `private` |
| --------------------------------- | -------- | ----------- | --------- | --------- |
| The same class                    | Yes      | Yes         | Yes       | Yes       |
| A subclass in the same package    | Yes      | Yes         | Yes       | No        |
| Another class in the same package | Yes      | Yes         | Yes       | No        |
| A subclass in another package     | Yes      | Yes         | No        | No        |
| Another class in another package  | Yes      | No          | No        | No        |

(* No modifier.)

*/

/* This is a public class, so it can be accessed from anywhere. */
public class AccessModifiersInJava {

  /*
  This is a private member, so it can be accessed only from within this
  class.
  */
  private int privateMember;

  /*
  By default, without an access modifier, a member is public to the package,
  but not accessible outside of the package – not even via a subclass.
  */
  int defaultMember;

  /*
  This is a protected member. It is public to the package, but also accessible
  outside of the package via subclasses.
  */
  protected int protectedMember;

  /*
  This is a public member, so it can be accessed from any class in any package.
  */
  public int publicMember;

  /*
  This is a private method, so it can be accessed only from within this
  class.
  */
  private void privateMethod() {
    System.out.println("privateMethod() called");
  }

  /*
  By default, without an access modifier, a method is public to the package,
  but not accessible outside of the package, not even via a subclass defined
  in other packages.
  */
  void defaultMethod() {
    System.out.println("defaultMethod() called");
  }

  /*
  This is a protected method, so it can be accessed from anywhere within
  the same package, and from any subclass, even subclasses in different
  packages.
  */
  protected void protectedMethod() {
    System.out.println("protectedMethod() called");
  }

  /*
  This is a public method, so it can be accessed from anywhere.
  */
  public void publicMethod() {
      System.out.println("publicMethod() called");
  }
}

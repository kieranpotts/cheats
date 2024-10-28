/*
There are different types of error in Java:

* *Compile-time errors* are syntax errors, type checking errors, etc. These are
  detected by the compiler and must be fixed before the program can run. These
  are the easiest to detect and fix.

* *Runtime errors* are exceptions that occur during the execution of a program.
  These are not detected by the compiler but instead occur during the execution
  of the program. In Java, these are *exceptions* and they SHOULD be handled.

* *Logical errors* (aka. bugs) are errors in the program's logic that cause it
  to behave in a way that the developer did not intend. These are the most
  difficult to detect and fix – they require debugging and testing.

Exception handling
==================

In Java, an exception is an event that occurs during the execution of a program
– ie. at runtime – that disrupts the normal flow of instructions. When an
exception occurs, the program terminates abnormally.

Exception handling is a mechanism to handle runtime errors so that the normal
flow of the program can be maintained. See below for code examples.

Exception hierarchy
===================

In Java, every type of object, including `Exception` objects, is a subclass of
 the `Object` class.

----
Object
  Throwable
    Error
    Exception
      RuntimeException
        ArithmeticException
        ArrayIndexOutOfBoundsException
        NullPointerException
        ...
      IOException
      SQLException
      ...
----

NOTE: Most names that end with "able" are actually interfaces, eg `Runnable`,
`Serializable`, `Cloneable`, etc. But `Throwable` is an exception to this rule
– it is actually a class.

There are two types of throwable objects in Java:

1. *Exception*: These are exceptional conditions that a applications SHOULD
  anticipate and recover from (ie handle using the `try`/`catch` construct).
  Examples include file not found exceptions (`FileNotFoundException`), network
  connection errors (`IOException`), and arithmetic errors (`ArithmeticException`).

2. *Error*: These are exceptional conditions that are external to the application,
  and that the application usually cannot anticipate or recover. Examples
  include out-of-memory errors (`OutOfMemoryError`) and stack overflow errors
  (`StackOverflowError`). Errors are typically ignored in code because you can
  rarely do anything about an error. Other examples include `ThreadDeath`,
  `IOError`, and `VirtualMachineError`.

There are lots of further subclasses of `Exception`, such as `RuntimeException`,
`IOException`, `SQLException`, and many more.

`RuntimeException` is a special type of exception, because it is not checked by
the compiler. These are also known as *unchecked exceptions*. Other examples of
unchecked exceptions include `ArithmeticException`, `ArrayIndexOutOfBoundsException`,
and `NullPointerException`. It is up to the developer to choose whether or not
to handle these exceptions – but, as a general rule, it is best practice to do so.

All other types of `Exception` are checked by the compiler. These are known as
*checked exceptions*. Methods that may throw checked exceptions MUST declare
them in the method signature using the `throws` keyword, and consuming code
MUST catch or rethrow them. Examples include `IOException`, `SQLException`, and
`ClassNotFoundException`.

The `throw` and `throws` keyword
================================

The `throw` keyword is used to raise an exception. A new `Exception` object
is created and thrown. Optionally, you can also provide a message with the
exception.

----
throw new ArithmeticException("Divide by zero");
----

The `throws` keyword is used to define the types of exceptions that a method may
throw (rather than the method handling those exceptions itself). This is
sometimes called "ducking the exception", because the method is not handling
the exception itself but is instead passing it on to the calling code to handle.
The exception will propagate up the call stack until it is caught – else the
program will terminate.

`throws` SHOULD be used when a method throws a checked exception that it does
not handle itself. It is not necessary to use `throws` for unchecked exceptions
(ie `RuntimeException` instances).

----
class A {
  public void fn() throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
  }
}

public class Main {
  public static void main(String[] args) {
    A a = new A();
    try {
      a.fn();
    } catch (ClassNotFoundException e) {
      e.printStackTrace(); // This can be helpful for debugging.
    }
  }
}
----

Note, you could also do the following, but this is never recommended for `main`
methods, because those are called by the JVM and therefore the exception will
never be handled and the program will not, therefore, fail gracefully.

----
public class Main {
  public static void main(String[] args) throws ClassNotFoundException {
    A a = new A();
    a.fn();
  }
}
----

Custom exceptions
=================

It is RECOMMENDED to extend the most appropriate `Exception` class, which most
of the time will be `RuntimeException`.

----
class MyCustomException extends RuntimeException {
  public MyCustomException(String message) {
    super(message);
  }
}

try {
  // …
} catch (MyCustomException e) {
  System.out.println(e.getMessage());
}

throw new MyCustomException("This is a custom exception");
----

*/

public class ExceptionsInJava {
  public static void main(String[] args) {

    /* This is a normal statement. Nothing can really go wrong: */
    int i = 4;

    /*
    This is a critical statement. It can throw an exception,
    if the value of i is 0.
    */

    @SuppressWarnings("unused")
    int j = 8/i;

    /*
    For critical statements, even if you think it is unlikely that
    anything will go wrong, it is always better to handle possible
    exceptions. This is done using `try/catch` blocks.
    */

    int divider = 0;
    int x = 0; // default value

    try {
      x = 10 / divider;
    } catch (ArithmeticException e) {
      System.out.println("Arithmetic Exception: " + e);
    }

    /*
    Where `try` encapsulates quite a bit of complex logic, where you might not
    be certain about all the possible kinds of exceptions that could be thrown,
    it is RECOMMENDED to catch all possible exceptions. (Make sure parent
    exceptions are caught after child ones.)
    */

    catch (Exception e) {
      System.out.println("Exception: " + e);
    }

    System.out.println("Value of x: " + x);
  }
}

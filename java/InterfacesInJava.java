/*
There are three types of interface in Java:

* *Normal interfaces*: any interface with two or more methods.

* *Functional interfaces*: (aka SAM, Single Abstract Method) an interface with
  only one abstract method. Built-in examples include `Runnable`, `Callable`,
  `Comparator`, `ActionListener` etc. We can create our own functional
  interfaces as well.

* *Marker interfaces*: an interface with no methods, aka a blank interface.
  Examples include `Serializable`, `Cloneable`, `Remote`, `RandomAccess` etc.
  These interfaces are used to provide some special information to JVM or
  compiler. For example, a class that implements `Serializable` interface can
  be serialized and deserialized, which means its state (data) can be saved and
  restored from disk.

All methods in an interface are `public` and `abstract` by default.

NOTE: From Java 8 (released 2014) onwards, we can have default and static
methods defined directly in interfaces themselves.
*/

public class InterfacesInJava {
  public static void main(String[] args) {
    Laptop laptop = new Laptop();
    Desktop desktop = new Desktop();

    Developer developer = new Developer();
    developer.code(laptop);
    developer.code(desktop);

    /* Or use an interface to define the code method: */
    developer.doCoding(laptop);
    developer.doCoding(desktop);

    System.out.println("Max: " + Computer.max); // "Max: 100"
  }
}

interface Computer {

  /*
  All methods declared in an interface are `public abstract` by default.
  */

  void code();

  /*
  You can also define variables in an interface. They must be initialized
  when declared - because, in interfaces, variables are `public static final`
  by default. This means they are constants. Including these in an interface
  just means there's a single place to define them for all classes that
  implement the interface. Everything else defined in an interface MUST
  be explicitly implemented in the implementing classes.
  */

  int max = 100;
}

interface X {
  void run();
}

/* Interfaces can extend other interfaces. */
interface Y extends X {
  void jump();
}

class Desktop implements Computer {
  public void code() {
    System.out.println("Coding on Desktop");
  }
}

/*
Classes can implement multiple interfaces.
(This is not possible with inheritance, eg. from abstract classes.)
*/

class Laptop implements Computer, Y {
  public void code() {
    System.out.println("Coding on Laptop");
  }

  /* Required from the X interface. */
  public void run() {}

  /* Required from the Y interface. */
  public void jump() {}
}

class Developer {
  public void code(Laptop laptop) {
    System.out.println("Coding on Laptop");
  }

  public void code(Desktop desktop) {
    System.out.println("Coding on Desktop");
  }

  public void doCoding(Computer computer) {
    computer.code();
  }
}

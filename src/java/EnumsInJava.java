/*
Enums are named groups of constants.

In Java, enums are a special kind of class, with methods. They are a special
type of class, for example they can't be extended from other classes. But you
can do lots of things with enums like define variables inside them, and
even define custom constructors. Enums extend `Object`, like classes do, so you
can all the usual methods like `toString()`, `equals()`, etc.

And all the possible values of an enum are also individual objects.
*/

public class EnumsInJava {
  public static void main(String[] args) {
    Status status = Status.Running;
    System.out.println(status); // "Running"

    /* You can also get the index (zero-based) of an enum value. */
    System.out.println(status.ordinal()); // 0

    /* There are other built-in methods. */
    Status[] statuses = Status.values();
    for (Status s : statuses) {
      System.out.println(s + ": " + s.ordinal());
    }

    /* You can compare enums. */
    Status s1 = Status.Paused;
    Status s2 = Status.Stopped;

    if (s1 == Status.Running)
      System.out.println("Running");
    else
      System.out.println("Not Running");

    /* Switch statements can be used with enums, too. */
    switch (s2) {
      case Running: // no need for `Status.` prefix
        System.out.println("Running");
        break;
      case Stopped:
        System.out.println("Stopped");
        break;
      case Paused:
        System.out.println("Paused");
        break;
    }

    /* Enum methods. */
    System.out.println(s2.getClass().getSuperclass()); // "class java.lang.Enum"

    /* Usage example of advanced enums: */
    Laptop laptop = Laptop.XPS;
    System.out.println(laptop.getPrice()); // 2200

    for(Laptop l : Laptop.values()) {
      System.out.println(l + ": " + l.getPrice());
    }
  }
}

enum Status {
  Running, Stopped, Paused;
}

/*
This is an example of an enum with a constructor that takes a parameter
and sets an internal variable for each object in the enum. We can also
define methods inside an enum - in this case, we've created a getter
and setter.
*/

enum Laptop {
  Macbook(2000), XPS(2200), ThinkPad(1800), Surface(1500), Chromebook;

  private int price;

  private Laptop() {
    /* Default price, which will be used for Chromebook. */
    this.price = 0;
  }

  private Laptop(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  /*
  This allows the price to be changed for a laptop, even after
  the enum has been defined:
  */
  public void setPrice(int price) {
    this.price = price;
  }
}

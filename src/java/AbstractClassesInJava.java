public class AbstractClassesInJava {
  public static void main(String[] args) {

    /* Abstract classes cannot be instantiated. This will not compile. */
    // Car car = new Car();

    /* Only concrete classes can be instantiated. */
    Car car = new Mondeo();
    car.drive(); // "Driving Mondeo"
    car.park(); // "Parking"
  }
}

abstract class Car {

  /*
  It is not compulsory to have abstract methods in an abstract class. But
  the abstract methods must *all* be implemented in the subclasses that
  get instantiated.
  */

  public abstract void drive();

  // public abstract void fly();

  public void park() {
    System.out.println("Parking");
  }
}

class Mondeo extends Car {
  public void drive() {
    System.out.println("Driving Mondeo");
  }
}

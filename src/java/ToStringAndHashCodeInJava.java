public class ToStringAndHashCodeInJava {
  public static void main(String[] args) {

    /*
    The `toString()` method is used to get a string representation of an object.
    The default implementation of the `toString()` method returns the class name
    and the hash code of the object.
    */

    Object obj = new Object();
    System.out.println(obj); // "java.lang.Object@15db9742"

    /* Same as... */
    System.out.println(obj.toString()); // "java.lang.Object@15db9742"

    /*
    The `hashCode()` method is used to get the hash code of an object.
    The default implementation of the `hashCode()` method returns a unique
    integer value (hexadecimal format) for each object. The hash is based
    on the values of the object.
    */

    System.out.println(obj.hashCode()); // "366712642"

    /*
    The `toString()` and `hashCode()` methods can be overridden in a class to
    provide a more meaningful string representation and hash code.
    */

    Laptop laptop1 = new Laptop();
    laptop1.model = "Dell XPS 15";
    laptop1.price = 2000;

    System.out.println(laptop1); // "Model: Dell XPS 15, Price: 2000"
    System.out.println(laptop1.toString()); // "Model: Dell XPS 15, Price: 2000"

    /*
    The `equals()` method should also be overridden when the `hashCode()` method
    is overridden to maintain the general contract between the two methods.
    Internally, the `equals()` method uses the `hashCode()` method to compare
    if two objects encapsulate the same data (since the hashcode is based
    on the values of the object).
    */

    Laptop laptop2 = new Laptop();
    laptop2.model = "Dell XPS 15";
    laptop2.price = 2000;

    boolean result = laptop1.equals(laptop2);
    System.out.println(result); // false

    /*
    But if you uncomment the `equals()` method in the `Laptop` class, the result
    would be "true".
    */
  }
}

class Laptop {
  String model;
  int price;

  public String toString() {
    return "Model: " + model + ", Price: " + price;
  }

  /* This is a naive implementation of the equals() method: */
  // public boolean equals(Laptop that) {
  //   return this.model.equals(that.model) && this.price == that.price;
  // }

  /*
  Better to use your IDE to implement these kinds of methods.
  In VS Code, go to Context Menu > Source Action > Generate hashCode() and equals()...
  to generate the following methods:
  */

  // @Override
  // public int hashCode() {
  //   final int prime = 31;
  //   int result = 1;
  //   result = prime * result + ((model == null) ? 0 : model.hashCode());
  //   result = prime * result + price;
  //   return result;
  // }

  // @Override
  // public boolean equals(Object obj) {
  //   if (this == obj)
  //     return true;
  //   if (obj == null)
  //     return false;
  //   if (getClass() != obj.getClass())
  //     return false;
  //   Laptop other = (Laptop) obj;
  //   if (model == null) {
  //     if (other.model != null)
  //        return false;
  //   } else if (!model.equals(other.model))
  //     return false;
  //   if (price != other.price)
  //     return false;
  //   return true;
  // }
}

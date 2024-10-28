import java.util.HashMap;
// import java.util.Hashtable;
import java.util.Map;

public class MapsInJava {
  public static void main(String[] args) {

    /*
    Another widely used data structure in Java is the `Map`. A `Map` is an
    object that maps unique keys (rather than indexes) to values. In Java, the
    `Map` interface is part of the Collection API/Framework, though it does not
    extend the `Collection` interface itself.

    There are two main implementations of the `Map` interface in Java:
    `HashMap` and `Hashtable`. They behave almost identically. The main
    difference is that `Hashtable`s are synchronized, which means they are
    thread-safe.

    NOTE on the use of generics: Previously in Java it was necessary to specify
    the type of the key and the value in angle brackets, for both the
    declaration and the instantiation. Since Java 1.7 this is optional. If
    you specify the types on the interface, you can write empty angle
    brackets on the class, and the compiler will infer the types.
    */

    Map<String, Integer> students = new HashMap<>();
    // Map<String, Integer> students = new Hashtable<>();

    students.put("Alice", 56);
    students.put("Bob", 87);
    students.put("Charlie", 78);
    students.put("David", 91);

    /* Overwrites the previous value. */
    students.put("Alice", 99);

    /* Remove an element from the map. */
    students.remove("David");

    /* Print the whole map. It's not ordered. */
    System.out.println(students); // {Bob=87, Alice=99, Charlie=78}

    /* Print an individual element. */
    System.out.println(students.get("Alice")); // 99

    /* Print the values one-by-one. */
    for (Integer value : students.values()) {
      System.out.println(value);
    }

    /* Print both the keys and their corresponding values. */
    for (String key : students.keySet()) {
      System.out.println(key + " : " + students.get(key));
    }
  }
}

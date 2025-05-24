public class StringsInJava {
  public static void main(String[] args) {
    String name = "Kieran";
    System.out.println(name); // "Kieran"

    /* Equivalent to: */
    String name2 = new String("Kieran");
    System.out.println(name2); // "Kieran"

    /* Equivalent to: */
    char name3[] = {'K', 'i', 'e', 'r', 'a', 'n'};
    System.out.println(name3); // "Kieran"

    /* Some string methods: */
    System.out.println(name.hashCode()); // -2047689704
    System.out.println(name.length()); // 6
    System.out.println(name.charAt(0)); // 'K'

    /*
    Strings are actually immutable in Java. In the following example,
    `n1` and `n2` exist on the stack, but they both point to the same
    `String` object that is stored in the heap memory. There's a thing
    called the string pool, which is a special area of heap memory
    for storing all the string values currently in use. If you create
    a new string with the same value as one that already exists in the
    pool, then the new variable (in the stack) will just point to the
    existing string value in the pool.
    */

    String n1 = "Kieran";
    String n2 = "Kieran";
    System.out.println(n1 == n2); // true (means the addresses are the same)

    /*
    String concatenation is still possible. This actually create a new
    string in the pool, with the value "Kieran Potts", and then the
    name variable is updated to point to this new string value/object.
    Garbage collection will then delete the old string value/object,
    if nothing else is pointing to it from the stack.
    */

    name = name + " Potts";
    System.out.println(name); // "Kieran Potts"

    /*
    If you want genuine mutable string, you can use StringBuffer or
    StringBuilder. The difference between the two is that StringBuffer
    is thread-safe, whereas StringBuilder is not. So, if you're using
    multiple threads, you should use StringBuffer, otherwise use
    StringBuilder.
    */

    /* StringBuffer example: */
    StringBuffer sb = new StringBuffer("Kieran");

    /* Returns 16, the default capacity, which is the number of characters
    that can be stored in the buffer without it having to resize itself. */
    int bfSize = sb.capacity();
    System.out.println(bfSize);

    /* Length may be different from the capacity. */
    System.out.println(sb.length()); // 6

    /* Appending more characters to the string buffer. It's capacity will
    automatically increase if necessary. */
    System.out.println(sb); // "Kieran"
    sb.append(" Potts");
    System.out.println(sb); // "Kieran Potts"

    /* There are lots of other methods for manipulating string buffers,
    eg `deleteCharAt`, `insert`, `reverse`, etc. */
    sb.reverse();

    /* Returns the string buffer value as a standard String. */
    String str = sb.toString();
    System.out.println(str); // "sttoP nareiK"

    /*
    StringBuilder is NOT thread-safe, but otherwise it's the same as
    StringBuffer, which IS thread-safe. That's the only difference.
    */
  }
}

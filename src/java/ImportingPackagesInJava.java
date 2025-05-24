/* This is importing the Packages class from the tools package. */
import tools.PackagesInJava;

/*
Java also has a lot of built-in packages that you can import.
For example, the `ArrayList` class is in the `java.util` package.
*/
import java.util.ArrayList;

/*
You can also import all classes from a package. The star means import all the
`.java` files from the package, but not sub-packages (which are in sub-directories).
*/

// import java.util.*;

/*
All Java classes will have the `java.lang` package imported by default.
In other words, the follow statement is implicitly added to the top of
every Java file. The `System` class is in the `java.lang` package, and is
therefore automatically available everywhere.
*/

// import java.lang.*;

public class ImportingPackagesInJava {
  public static void main(String[] args) {
    PackagesInJava.main(args);

    ArrayList<Object> list = new ArrayList<>();
    System.out.println("ArrayList: " + list);

    /*
    System is not explicitly imported, but it is done so implicitly
    from the `java.lang` package.
    */

    System.out.println("Hello, World!");
  }
}

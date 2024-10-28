

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsInJava {
  public static void main(String[] args) {

    /*
    Java 1.2 introduced the Collection Framework, also known as the
    Collection API, which provides a convenient way to implement data
    structures such as stacks (last-in-first-out lists) and queues
    (first-in-first-out lists). These data structures can be implemented
    using basic arrays, but it is harder to do because of the constraint
    that arrays in Java have a fixed size.

    The Collection Framework is more convenient. It is a built-in library
    of classes and interfaces that implement commonly reused data structures.

    The Collection interface belongs to the `java.util` package,
    so it must be explicitly imported. This differs from the core
    Java classes, such as String, which belong to the `java.lang` package
    and are therefore automatically imported.

    `Collection` itself is an interface, so you cannot create an instance of it.
    Java also provides extended interfaces that inherit from `Collection`.
    Examples include `List`, `Queue` and `Set`. `Collection` itself extends the
    `Iterable` interface, which provides a way to iterate over the elements
    of an array-like object.

    Java also provides a number of concrete classes that implement the
    `Collection` interface, and its derived interfaces. For example,
    the `List` interface is implemented by the `ArrayList` and `LinkedList`
    classes, the `Set` interface is implemented by the `HashSet` and
    `LinkedHashSet` classes, and the `Queue` interface is implemented by the
    `DeQueue` class.

    ----
    Iterable
      Collection
        List
          ArrayList
          LinkedList
        Set
          HashSet
          LinkedHashSet
          TreeSet
        Queue
          DeQueue
    ----

    `ArrayList` and `HashSet` are the most commonly-used built-in
    data structures in Java. `ArrayList` is an ordered collection of
    objects that allows duplicate values, while `HashSet` is an unordered
    collection of unique objects.

    By default, all collections are collections of *objects*. To store
    other types of data we SHOULD use generics. In angle brackets, we specify
    the type of data that the collection holds. This helps to catch type
    errors at compile time, rather than at runtime.
    */

    Collection<Integer> collection = new ArrayList<Integer>();

    collection.add(1);
    collection.add(5);
    collection.add(8);
    collection.add(2);

    /* Duplicate values are allowed in `Collection`s and `List`s. */
    collection.add(2);

    /* Print the whole collection on a single line. */
    System.out.println(collection);

    /* Print one value per line. */
    for (int n : collection) {
        System.out.println(n);
    }

    /* Alternatively the `Iterator` interface can be used. */
    Iterator<Integer> values = collection.iterator();
    while (values.hasNext())
      System.out.println(values.next());

    /*
    A `Collection` can be iterated over but it does not support index values.
    To use index values, we should use an implementation of the `List` interface.
    You can then read specific values by their index, via the `get()` method.
    */

    List<Integer> list = new ArrayList<Integer>();

    list.add(1);
    list.add(5);
    list.add(8);
    list.add(2);

    System.out.println(list.get(2));

    /*
    The `Set` interface is a collection that contains no duplicate elements.
    The `Set` interface is implemented by the `HashSet` and `LinkedHashSet`
    classes. The `HashSet` class is the most commonly-used implementation of
    the `Set` interface.
    */

    Set<Integer> set = new HashSet<Integer>();

    set.add(1);
    set.add(5);
    set.add(8);
    set.add(2);

    /*
    Duplicate values are NOT allowed in `Set`s. This next line will not
    produce an error, neither at compile time nor at runtime, but the
    value will not be added to the `Set`, because it already exists there.
    */

    set.add(2);

    for (int n : set) {
      System.out.println(n);
    }

    /*
    In Java, `Set`s are not ordered. To have sorted values, use `TreeSet`
    instead.
    */

    Set<Integer> treeSet = new TreeSet<Integer>();

    treeSet.add(1);
    treeSet.add(5);
    treeSet.add(8);
    treeSet.add(2);

    for (int n : treeSet) {
      System.out.println(n);
    }

    /*
    Java also provides a class called `Collections` (with an "s" on the end),
    which contains some useful static methods for working with `Collection`
    (no "s") instances. For example, the `sort()` method can be used to sort
    a List.
    */

    List<Integer> unsorted = new ArrayList<Integer>();

    unsorted.add(51);
    unsorted.add(15);
    unsorted.add(80);
    unsorted.add(23);

    Collections.sort(unsorted);

    System.out.println(unsorted); // [15, 23, 51, 80]

    /*
    You can also sort values using your own logic. In the following example,
    we sort the values by their last digit, not the whole number.

    A *comparator* (aka a comparison function) is used to compare two
    elements of a collection. The function must return 1 if we want the
    two compared values to be swapped around. This process is repeated on
    each pair of elements in the collection until the collection is sorted
    – a classic sorting algorithm.
    */

    Comparator<Integer> comparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer i, Integer j) {
        if (i%10 > j%10)
          return 1;
        else
          return -1;
      }
    };

    Collections.sort(unsorted, comparator);

    System.out.println(unsorted); // [80, 51, 23, 15]

    /*
    In the following example, the students are sorted by their age.
    An anonymous class is used here to implement the `Comparator` interface.
    */

    List<Student> students = new ArrayList<>();

    students.add(new Student("Alice", 21));
    students.add(new Student("Bob", 18));
    students.add(new Student("Charlie", 19));
    students.add(new Student("David", 29));

    Collections.sort(students, new Comparator<Student>() {
      @Override
      public int compare(Student s1, Student s2) {
        if (s1.age > s2.age)
          return 1;
        else
          return -1;
      }
    });

    /*
    Since `Comparator` is a functional interface, we could simplify
    the above code further using a lambda expression. We can also
    replace the `if/else` statement with a ternary operator. You can also
    remove the types from the lambda call – so the above code can be
    condensed into a single line.
    */

    // Collections.sort(students, (s1, s2) -> return s1.age > s2.age ? 1 : -1);

    for (Student student : students) {
      System.out.println(student);
    }

    /*
    Built-in wrapper classes implement an interface called `Comparable`,
    which allows them to be compared with each other, and therefore
    sorted without needing to provide a `Comparator`.

    For custom classes, we have to define a custom `Comparator`. An alternative
    design is for the custom classes to implement the `Comparable` interface.
    This, the comparison logic moves to the classes themselves.
    The `compareTo()` method of the `Comparable` interface is used
    automatically by the `Collections.sort()` method. An example of this
    can be seen in the `Teachers` class implementation, below.
    */

    List<Teacher> teachers = new ArrayList<>();

    teachers.add(new Teacher("Mr Arnold", 51));
    teachers.add(new Teacher("Mrs Brown", 38));
    teachers.add(new Teacher("Miss Clark", 29));
    teachers.add(new Teacher("Dr Davis", 49));

    /* No comparator argument required! */
    Collections.sort(teachers);

    for (Teacher teacher : teachers) {
      System.out.println(teacher);
    }

    /*
    Different ways to iterate over arrays.
    (This also demonstrates a different way of creating Lists.)
    */

    List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

    /* Basic for loop. */
    for (int i = 0; i < nums.size(); i++) {
      System.out.println(nums.get(i));
    }

    /* Enhanced for loop. */
    for (int n : nums) {
      System.out.println(n);
    }

    /* The foreach method (part of the List interface, introduced Java 1.8). */
    nums.forEach(n -> System.out.println(n));
  }
}

class Student {
  String name;
  int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student [name=" + name + ", age=" + age + "]";
  }
}

class Teacher implements Comparable<Teacher> {
  String name;
  int age;

  public Teacher(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Teacher [name=" + name + ", age=" + age + "]";
  }

  @Override
  public int compareTo(Teacher that) {
    if (this.age > that.age)
      return 1;
    else
      return -1;
  }
}

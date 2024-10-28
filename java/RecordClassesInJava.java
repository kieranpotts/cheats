/*
In object-oriented programming there will be many use cases when you create
*functional classes* that do some sort of processing. Service classes and
utility classes fall into this category. But there will be times when you need
to create pure *data classes*, which exist only to store data. These types of
classes are often called *data carriers* or *data transfer objects* (DTOs).

Example of a simple, immutable data entity type:
*/

import java.util.Objects;

public class RecordClassesInJava {
  public static void main(String[] args) {
    /* Usage example: */
    Person p = new Person("Alice", 30);
    System.out.println(p.getName() + " is " + p.getAge() + " years old.");
  }
}

class Person {
  private final String name;
  private final int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && Objects.equals(name, person.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + "}";
  }
}

/*
But that's a lot of boilerplate code to create valid data objects in Java! For
example, the `equals()` method is necessary to compare two objects of the same
type. Without it, the following code will return `false`.

----
Person p1 = new Person("Alice", 30);
Person p2 = new Person("Alice", 30);

System.out.println(p1.equals(p2)); // true
----

Likewise, implementation of the `toString()` method is necessary for a full
implementation (it is called by methods such as `System.out.println()` and
`String.valueOf()`).

In Java since version 17, it is much, _much_ easier to create this kind of
class using the `record` keyword. All you need to do is:

----
record Person(String name, int age) {}
----

The arguments are the record's state variables.

Behind the scenes, the Java compiler will generate all the code necessary to
create a useful data object, including the constructor and implementations of
the `equals()`, `hashCode()`, and `toString()` methods. The state variables will
all be `private` and `final`.

You can override things if you need to. For example, if you wanted to make any
of the state variables optional, you could create additional constructors with
reduced parameters (or no parameters at all). In the following example, the
`age` field becomes optional, defaulting to the value `0` if not provided.

----
record Person(String name, int age) {
  public Person(String name) {
    this(name, 0);
  }
}
----

This is called a non-canonical constructor, because it does not have the same
parameters as the canonical constructor, which is defined on the `record` line.
To implement a non-canonical constructor, it is REQUIRED that the canonical
constructor be called from within it. This can be done using `this()`.

You might also want to build in some validation to the constructor. For example,
you could check that the `age` is a positive number. If it is not, you could
throw an `IllegalArgumentException`. This is a good use case for a *compact
canonical constructor*. Instead of the following:

----
record Person(String name, int age) {
  public Person(String name, int age) {
    if (id == 0)
      throw new IllegalArgumentException("Age must be a positive number.");

    this.name = name;
    this.age = age;
  }
}
----

You can use the compact canonical constructor syntax instead. You're just
adding the validation checks that you want to do. If no exceptions are thrown,
Java will handle the assignments for you.

----
record Person(String name, int age) {
  public Person {
    if (id == 0)
      throw new IllegalArgumentException("Age must be a positive number.");
  }
}
----

Records can be extended with any custom methods you need, and they can also
implement interfaces. Implementation of the `Cloneable` interface, for example,
is a common design pattern for immutable data objects in Java.

----
record Person(String name, int age) implements Cloneable {
  @Override
  public Person clone() {
    return new Person(name, age);
  }
}
----

Values are accessed using method names that match the field names. For example,
to access the `name` field, you would use the `name()` method. To access the
`age` field, you would use the `age()` method.

----
System.out.println(p.name() + " is " + p.age() + " years old.");
----
*/

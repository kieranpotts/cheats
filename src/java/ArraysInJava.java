public class ArraysInJava {
  public static void main(String[] args) {

    /*
    In Java, an array is a data structure that stores a fixed-size sequential
    collection of elements of the same type. An array is used to store a
    collection of data, but it is often more useful to think of an array as
    a collection of variables of the same type.

    Java's array type is a reference type, ie the elements of the array are
    references to objects in heap memory.

    Arrays are part of the core language, provided by the `java.lang`
    package, which does not need to be explicitly imported.

    The downside of arrays in Java is that, once you specify the length
    of an array, you cannot change it. That's because arrays are objects
    and they therefore exist in heap memory, thus they are stored as a
    continuous block of memory (this is true of all objects in heap memory).

    So, if you wanted to add more elements to an array, you would have to
    create a new array with a larger size, then copy the elements from the
    old array to the new array, and then delete the old array.

    Another (arguable) disadvantage is that arrays can only store elements
    of the same type. So, if you wanted to store different types of data,
    you would need to create an array of custom objects, and then cast the
    elements to their respective types when you retrieve them.

    This is a lot of work! So for dynamic arrays Java provides alternatives:
    ArrayLists and Collections.
    */

    /* An array of integers that are known from the start. */
    int arr1[] = {3,7,4,2};
    System.out.println(arr1[1]); // 7

    arr1[1] = 5;
    System.out.println(arr1[1]); // 5

    /*
    An array of integers with unknown initial values, which will
    therefore be 0 by default.
    */

    int arr2[] = new int[4];
    System.out.println(arr2[1]); // 0

    arr2[0] = 3;
    arr2[1] = 7;
    arr2[2] = 4;
    arr2[3] = 2;

    for (int i = 0; i < arr2.length; i++) {
      System.out.println(arr2[i]);
    }

    /*
    Multi-dimensional array. 3 rows, 4 columns.
    */

    int multiArr[][] = new int[3][4];

    /* Assign random values < 100. */
    for (int i = 0; i < multiArr.length; i++) {
      for (int j = 0; j < multiArr[i].length; j++) {
        multiArr[i][j] = (int) (Math.random() * 100);
      }
    }

    /* Print the values in a grid. */
    for (int i = 0; i < multiArr.length; i++) {
      for (int j = 0; j < multiArr[i].length; j++) {
        System.out.print(multiArr[i][j] + " ");
      }
      System.out.println();
    }

    /* Alternative way to print the values in a grid. */
    for(int n[]: multiArr) {
      for(int element: n) {
        System.out.print(element + " ");
      }
      System.out.println();
    }

    /*
    If you want to have a multi-dimensional array where each row has a
    different number of columns, you can use a *jagged array*.

    Here, the first row has 3 columns, the second row has 2 columns, and
    the third row has 4 columns.
    */

    int jaggedArr[][] = new int[3][];
    jaggedArr[0] = new int[3];
    jaggedArr[1] = new int[2];
    jaggedArr[2] = new int[4];

    /* 3D arrays are also possible. */
    @SuppressWarnings("unused")
    int threeDArr[][][] = new int[3][4][5];

    /* You can also have arrays of objects. */
    Student s1 = new Student();
    s1.name = "John";
    s1.mark = 90;

    Student s2 = new Student();
    s2.name = "Mary";
    s2.mark = 80;

    Student s3 = new Student();
    s3.name = "Peter";
    s3.mark = 70;

    Student students[] = {s1, s2, s3};

    /* Or: */
    // Student students[] = new Student[3];
    // students[0] = s1;
    // students[1] = s2;
    // students[2] = s3;

    for (Student student: students) {
       System.out.println(student.name + " " + student.mark);
    }

    /* Or: */
    // for (int i = 0; i < students.length; i++) {
    //     System.out.println(students[i].name + " " + students[i].mark);
    // }
  }
}

class Student {
  String name;
  int mark;
}

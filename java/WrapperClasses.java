/*
Java is 99% object-oriented, but it is not 100% object oriented because it
supports primitive data types. For example, int, float, etc. are primitive
data types. They are not objects, as they do not have methods.

The advantage of primitive data types is improved performance. Low-level
primitive values do not need to be stored in heap memory as objects.

For every primitive data type, there is a class in Java that represents it.
These classes are called wrapper classes. They are used to convert primitive
data types into objects and vice versa.

    int -> Integer
    char -> Character
    double -> Double
    etc.
*/

public class WrapperClasses {
    public static void main(String[] args) {
        int num = 7;

        /*
        This is how we store a primitive data type in an object.
        This concept is called boxing. This is the old syntax, and it is
        deprecated. It will emit an error on build:

            Integer i = new Integer(num);

        The new syntax does the conversion automatically. This is called
        autoboxing.
        */

        Integer i = num;

        System.out.println(i); // 7

        /* To get the primitive data type back, we use unboxing. */
        int num2 = i.intValue();
        System.out.println(num2); // 7

        /* You can also drop the use of the `intValue` method and use
        auto-unboxing. */
        int num3 = i;
        System.out.println(num3); // 7

        /* You can also convert a string to an integer using `parseInt()`. */
        String str = "123";
        int num4 = Integer.parseInt(str);

        System.out.println(num4); // 123
    }
}

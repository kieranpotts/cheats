public class TypeConversionAndCastingInJava {
  public static void main(String[] args) {
    int a = 257;
    byte b = 127;

    /*
    This will not compile, because a (4 bytes) has a bigger range of
    possible values than b (1 byte). Therefore data would be lost if you did
    this.
    */

    // b = a;

    /*
    This will compile, because b has a smaller range of possible values
    than a, so will fit into it. This is an *implicit* conversion.
    */

    a = b;
    System.out.println(a); // 127

    int a2 = 257;
    byte b2 = 127;

    /*
    Or you can do this. This is called casting. In this case you are
    casting an integer value to a byte. The syntax tells the compiler that
    you know what you are doing, and that you understand that you might
    lose data in this operation. This is an *explicit* conversion.

    After this operation, `b2` will be 1, because 257 % 256 = 1. The modular
    operator `%` returns the remainder after dividing the first operand by
    the second operand. 257 is the value of `a`. 256 is the maximum number of
    possible values in a byte (the values range from from -128 to +127).
    */

    b2 = (byte) a2;
    System.out.println(b2); // 1

    /*
    This will not compile, because you are trying to assign a double
    to an int.
    */

    // int x = 5.6;

    /*
    You need to be explicit if you want to assign a float/double value to an
    integer. In this operation, you will lose the precision after the decimal
    point.
    */

    float f = 5.6f;
    int x = (int) f;
    System.out.println(x); // 5

    /*
    Results of operations must be saved in data types that can hold the result.
    Even though the operands are bytes, the result of the multiplication is an
    int, so Java will automatically promote the result to an int value. This is
    called type promotions.
    */

    byte ten = 10;
    byte thirty = 30;
    int result1 = ten * thirty;
    System.out.println(result1); // 300

    /*
    This will not compile, because you're trying to assign an int
    value to a byte.
    */

    // byte result2 = ten * thirty;
  }
}

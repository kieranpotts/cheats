class PrimitivesInJava {
  @SuppressWarnings("unused")
  public static void main(String a[]) {

    /* Primitive Data Types */

    /* byte, 1 byte, stores whole numbers -128 to 127. */
    byte by = 127;

    /* short, 2 bytes, stores whole numbers -32768 to 32767. */
    short sh = 558;

    /* int, 4 bytes, stores whole numbers -2147483648 to 2147483647. */
    int nm = 9;

    /* long, 8 bytes, stores whole numbers -9223372036854775808 to 9223372036854775807. */
    long l = 5854l;

    /* float, 4 bytes, stores fractional numbers of 6 to 7 decimal digits. */
    float num4 = 5.6f;

    /* double, 8 bytes, stores fractional numbers of 15 decimal digits. */
    double num3 = 5.6;

    /* char, 2 bytes, stores a single character/letter or ASCII/Unicode values. */
    char c = 'k';

    /* boolean, 1 bit, stores true or false values. */
    boolean b = true;

    int result = nm + 1;
    System.out.println(result);

    /*
    Integers are Base 10 by default. You can specify base 8 or 16 by adding
    0 or 0x prefix respectively, and you can specify base 2 (ie a binary
    literal, a number represented only by 0s and 1s) by adding the 0b or 0B
    prefix.
    */

    /* Base 2 */
    int base2 = 0b101;
    System.out.println(base2); // 5

    /* Hexadecimal (Base 16) */
    int hex = 0x1F;
    System.out.println(hex); // 31

    /*
    For readability, you can use the underscore character _ to separate
    groups of digits in numeric literals.
    */

    int cleanNumber = 1_000_000_000;
    System.out.println(cleanNumber);

    /*
    For very big numbers – bigger than the range of long – you can use the
    epsilon notation (e):
    */

    double bigNumber = 12e10;
    System.out.println(bigNumber);

    /*
    The `++` and `--` operators increment and decrement by 1 respectively.
    This can be used on chars as well as ints.
    */

    char ch = 'c';
    ch++;
    System.out.println(ch); // 'd'
  }
}

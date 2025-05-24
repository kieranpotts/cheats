

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamApiInJava {
  public static void main(String[] args) {

    /*
    Java 8 introduced the Stream API, which is used to process collections
    of objects. The following is an example use case. We have a list of
    numbers. We want to filter out only the even numbers, double their
    values, then reduce the list to a single number that is a sum of all
    the processed values.

    The Stream API allows us to do this sort of thing in a more declarative
    style, rather than the imperative style that was previously more
    commonly used in Java.

    What's also nice about the Stream API is that the values are transferred
    to the stream and processed, but the original list is a separate object
    and is not modified. Thus, streams can be used to make our code more
    thread-safe.

    Once you have used a stream, you can't reuse it. Thus you could not
    use `forEach()`, for example, twice on the same stream.
    */

    List<Integer> numbers = Arrays.asList(4, 5, 7, 2);

    System.out.println(numbers); // [4, 5, 7, 2]

    /* Imperative style. */

    // int sum = 0;
    // for (int n : numbers)
    // {
    //   if (n%2 == 0)
    //   {
    //     n = n * 2;
    //     sum = sum + n;
    //   }
    // }
    //
    // System.out.println(sum); // 12

    /* Declarative style. */

    Stream<Integer> stream = numbers.stream();

    /* Each operation on a stream returns a new stream. */

    // stream = stream.filter(n -> n % 2 == 0);
    // stream = stream.map(n -> n * 2);
    // stream = stream.reduce(0, Integer::sum);

    /* Using method chaining. */
    int sum = stream
      .filter(n -> n % 2 == 0)
      .map(n -> n * 2)
      .reduce(0, Integer::sum);

    /*
    Use `parallelStream` to make use of multiple threads, where beneficial.
    Careful. This is not always thread-safe. It depends on the operations
    you are performing. Avoid using methods that compare two elements,
    such as `sorted()`.
    */

    // int sum = numbers.parallelStream()
    //   .filter(n -> n % 2 == 0)
    //   .map(n -> n * 2)
    //   .reduce(0, Integer::sum);

    System.out.println(sum); // 12
  }
}

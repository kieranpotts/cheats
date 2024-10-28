/*
Modern operating systems support multi-tasking, which means that multiple
processes (each running a discrete program) can run concurrently. In practice,
multi-tasking is implemented by the operating system switching between processes
so quickly that it appears as if they are running simultaneously. This is known
as time-sharing or time-slicing.

(Multi-tasking is different from multi-processing, which means that multiple
processes can run on multiple processors. Multi-core CPUs, which have multiple
processing units on a single chip, enable multi-processing on a single device.)

Each process can have multiple threads, which are the smallest unit of execution
within a process. Threads within a task share the same memory space and
resources, but each thread has its own call stack and local variables.

Threads are a lightweight way to achieve parallelism within a process. Threads
can be used by a single program to perform multiple tasks simultaneously, such
as downloading files while displaying a progress bar, or processing data while
updating a user interface.

Java supports multi-threading, which means that a Java program can have multiple
threads running concurrently.
*/

/*
To design a class to execute in a separate thread, it's as
simple as extending the Thread class.
*/

class Z extends Thread {
  public void run() {
      // …
  }
}

/*
However, this is NOT the recommended way to create a thread in Java.
The recommended way is to implement the `Runnable` interface.
This is because Java does not support multiple inheritance, and
if you extend the `Thread` class, you cannot extend any other class.

The built-in `Thread` class also implements the `Runnable` interface.
So, when you extend the `Thread` class, you're actually implementing
the `Runnable` interface, anyway.
*/

class A implements Runnable {
  public void run() {
    for(int i=1;i<50;i++) {
        System.out.println("hi");

      /*
      The `sleep()` method is used to pause the execution of the current
      thread for a specified period of time. This gives the scheduler
      a chance to run other threads. We've only included this here to
      demonstrate the interleaving of the two threads in the output.
      */

      try {
        Thread.sleep(10);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

// class B implements Runnable {
//   public void run() {
//     for(int i=1;i<50;i++) {
//       System.out.println("hello");
//
//       try {
//         Thread.sleep(10);
//       } catch(InterruptedException e) {
//         e.printStackTrace();
//       }
//     }
//   }
// }

public class ThreadsInJava {
  public static void main(String[] args) {

    /* If extending the Thread class, do this: */
    // Thread a = new A();
    // Thread b = new B();

    /*
    If implementing the `Runnable` interface, do the following.
    The second demonstrates implementation using an anonymous class.
    */

    Runnable a = new A();
    Runnable b = new Runnable() {
      public void run() {
        for(int i=1;i<50;i++) {
          System.out.println("hello");

          try {
            Thread.sleep(10);
          } catch(InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    /*
    Note, `Runnable` is also a functional interface, so you can
    alternatively use a lambda expression to implement it.
    */

    // Runnable b = () -> {
    //   for(int i=1;i<50;i++) {
    //     System.out.println("hello");
    //     try {
    //       Thread.sleep(10);
    //     } catch(InterruptedException e) {
    //       e.printStackTrace();
    //     }
    //   }
    // };

    Thread ta = new Thread(a);
    Thread tb = new Thread(b);

    /*
    The priority of a thread is an integer value between 1 and 10.
    1 is the lowest priority, 10 is the highest.
    The default priority of a thread is 5.
    */

    // System.out.println(a.getPriority());
    // System.out.println(b.getPriority());
    System.out.println(ta.getPriority());
    System.out.println(tb.getPriority());

    /* To change the priority of a thread: */
    // a.setPriority(10);
    // b.setPriority(Thread.MIN_PRIORITY); // or use built-in constants
    ta.setPriority(10);
    tb.setPriority(Thread.MIN_PRIORITY); // or use built-in constants

    // System.out.println(a.getPriority()); // 10
    // System.out.println(b.getPriority()); // 1
    System.out.println(ta.getPriority()); // 10
    System.out.println(tb.getPriority()); // 1

    /*
    To start the threads, we need to call the `start()` method.
    The `start()` method internally calls the `run()` method of the thread.
    Thus, classes that extend the `Thread` class must implement the `run()` method.
    */

    // a.start();
    // b.start();
    ta.start();
    tb.start();

    /*
    Expected output: lots of hi's to start with, interspersed with
    some hello's, the lots of hello's toward the end. THe priority is
    just a suggestion to the OS's scheduler how you would like threads
    to be scheduled. It's not a guarantee that the thread with the highest
    priority will always run first.
    */
  }
}

/*
Race conditions
===============

Be wary of doing mutations on the same data in multiple threads. If two threads
try to modify the same data at the same time, they can interfere with each other,
leading to unpredictable results.

This is known as a race condition.

To prevent race conditions, do one of the following:

* Work only with immutable data.
* Make methods thread-safe.

A thread-safe method means that it can be called by only one thread a time.
Thus, if Thread A is currently executing a method called `increment()`, then
Thread B will wait until Thread A is done before it too executes `increment()`.

In Java, you can use the `synchronized` keyword to "synchronize" a method or
block of code. Synchronization ensures that only one thread can access a shared
resource at a time, thus making the method thread-safe and preventing race conditions.

----
class Counter {
  private int count = 0;

  public synchronized void increment() {
    // This below statement is actually three operations: read the value, add one,
    // and then write the new value back to the count variable. If two
    // threads try to increment the count at the same time, they can
    // override each other's changes. The count can end up being less
    // than expected (2000, in this case).
    //
    // Including the synchronized keyword in the method signature stops this.


    count++;
  }

  public int getCount() {
    return count;
  }
}

public class Demo {
  public static void main(String[] args) {
    Counter counter = new Counter();

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });

    t1.start();
    t2.start();

    // Wait for the two thread to finish their work and rejoin
    // the main thread. If we did not do this, the threads would
    // continue to run in parallel and therefore the count (below)
    // would be unpredictable – a random number up to 2000.

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Expected output: 2000
    System.out.println(counter.getCount());
  }
}
----

Thread states
=============

A thread can be in one of several states:

* *New*: The thread has been created but has not yet started.
* *Runnable*: The thread is ready to run.
* *Running*: The thread is currently executing.
* *Waiting* (aka blocked): The thread is waiting indefinitely for another
  thread to perform a particular action.
* *Dead*: The thread has finished running.

The `start()` method transitions a thread from the *New* state to the *Runnable*
state. The thread is now waiting for the OS's scheduler to start. When the
scheduler starts the thread in the CPU, the JVM changes the thread's state to
*Running*. This is when the `run()` method is executed.

If the `sleep()` method is called, the thread transitions to the *Waiting*
state. The thread will remain in this state until the sleep time has elapsed,
at which point it will transition back to the *Running* state. Alternatively,
the `wait()` method can be called, which will also transition the thread to the
*Waiting* state, and it exits this state and returns to the *Runnable* state
when another thread calls `notify()` or `notifyAll()`.

The `run()` method contains the code that the thread will execute. When the
`run()` method finishes its execution, the thread transitions to the *Dead*
state. Threads can also be stopped with `stop()`, but this API is deprecated
because it can leave programs in inconsistent states.
*/

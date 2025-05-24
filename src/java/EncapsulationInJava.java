public class EncapsulationInJava {
  public static void main(String[] args) {
    Human human = new Human();

    /*
    This will not work, because age and name are private.
    */

    // human.age = 44;
    // human.name = "Kieran";
    // int age = human.age;
    // String name = human.name;

    /*
    Use the public getter and setter methods instead.
    */

    System.out.println(human.getName()); // ""
    human.setName("Kieran");
    System.out.println(human.getName()); // "Kieran"

    System.out.println(human.getAge()); // 0
    human.setAge(44);
    System.out.println(human.getAge()); // 44
  }
}

class Human {

  /*
  This is an example of encapsulation. The age and name variables are
  private, so they can only be accessed from within this class. To access
  them from outside the class, you need to use the public getter and
  setter methods.

  It is best practice to make all instance variables private, and provide
  public getter and setter methods for them.
  */

  /* 0 by default. */
  private int age;

  /* Assign default value, it would be null by default, otherwise. */
  private String name = "";

  /*
  These method names are the community standard, but you can use
  any names you like:
  */

  public int getAge() {
    return age;
  }

  public void setAge(int age) {

    /*
    Use this to refer to the instance variable, so the parameter
    can also be named age, else you would have to write:
    */

    // age = newAge;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

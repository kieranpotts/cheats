public class ConstructorsInJava {
  public static void main(String[] args) {

    /*
    The `Human()` constructor requires exactly one or two parameters.
    It cannot have none.
    */

    Human human = new Human("Kieran");
    human.setAge(44);

    System.out.println(human.getName()); // "Kieran"
    System.out.println(human.getAge()); // 44
  }
}

class Human {
  private int age; // defaults to 0
  private String name = "";

  /* Include this to support a parameter-less constructor. */
  // public Human() {}

  /* Parameterized constructor. */
  public Human(String name) {
    /* Do instance initialization here. */
    this.name = name;
  }

  /* Constructor with two parameters. */
  public Human(String name, int age) {
    /* Do instance initialization here. */
    this.name = name;
    this.age = age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

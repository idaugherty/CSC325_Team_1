/**
 * Represents a simple HelloWorld class that stores a name and age,
 * and provides methods to greet and introduce the person.
 */
public class HelloWorld {
    private String name;
    private int age;

    /**
     * Constructs a HelloWorld object with the specified name.
     * Age is set to 0 by default.
     * @param name the name of the person
     */
    public HelloWorld(String name) {
        this.name = name;
        this.age = 0; // Default age if not provided
    }

    /**
     * Constructs a HelloWorld object with the specified name and age.
     * @param name the name of the person
     * @param age the age of the person
     */
    public HelloWorld(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Prints a greeting message using the person's name.
     */
    public void greet() {
        System.out.println("Hello, " + name + "!");
    }

    /**
     * Prints a custom greeting message using the provided message and the person's name.
     * @param message the custom message to use in the greeting
     */
    public void greet(String message) {
        System.out.println(message + ", " + name + "!");
    }

    /**
     * Introduces the person by printing their name and age.
     */
    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}
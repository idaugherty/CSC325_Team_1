import java.util.Scanner;

/**
 * Application launcher for HelloWorld.
 * Gathers user input, handles exceptions, and demonstrates HelloWorld usage.
 */
public class AppLauncher {
    /**
     * The main entry point for the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        int age = 0;

        // Gather user name with validation
        while (true) {
            try {
                System.out.print("Enter your name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Gather user age with validation
        while (true) {
            try {
                System.out.print("Enter your age: ");
                String ageInput = scanner.nextLine().trim();
                age = Integer.parseInt(ageInput);
                if (age < 0) {
                    throw new IllegalArgumentException("Age cannot be negative.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer for age.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Instantiate HelloWorld objects and call methods
        HelloWorld user = new HelloWorld(name, age);
        user.greet();
        user.greet("Welcome");
        user.introduce();

        HelloWorld student1 = new HelloWorld("Rico Something");
        student1.greet();
        student1.greet("Welcome");
        student1.introduce();

        HelloWorld student2 = new HelloWorld("Bella Daugherty", 21);
        student2.greet();
        student2.greet("Hi");
        student2.introduce();

        scanner.close();
    }
}

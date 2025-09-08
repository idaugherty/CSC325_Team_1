package main;
import animals.Animal;
import animals.Bird;
import animals.Fish;
import animals.Mammal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages a collection of animals in the zoo and provides an interactive menu.
 */
public class ZooManager {
    /**
     * The list of animals in the zoo.
     */
    private final List<Animal> animals;

    /**
     * Constructs a new ZooManager with an empty animal list.
     */
    public ZooManager() {
        animals = new ArrayList<>();
    }

    /**
     * Adds a list of animals to the zoo's collection.
     * @param animalList the list of animals to add
     */
    public void addAnimals(List<Animal> animalList) {
        animals.addAll(animalList);
    }

    /**
     * Loads default animals into the zoo's collection.
     */
    public void loadAnimals() {
        animals.add(new Mammal("Elephant"));
        animals.add(new Fish("Bass"));
        animals.add(new Bird("Cardinal"));
        // Add more animals as needed
    }

    /**
     * Returns the list of animals in the zoo.
     * @return the list of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Displays the interactive menu for the zoo manager and handles user input.
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getUserChoice(scanner);
            handleMenuChoice(choice);
            if (choice == 4) {
                running = false;
            }
        }
        scanner.close();
    }

    /**
     * Prints the menu options.
     */
    private void printMenu() {
        System.out.println("\nZoo Manager Menu:");
        System.out.println("1. List All Animals");
        System.out.println("2. Make All Move");
        System.out.println("3. Make All Make Sound");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets a valid user choice from the scanner.
     * @param scanner the Scanner to use
     * @return the user's menu choice
     */
    private int getUserChoice(Scanner scanner) {
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume invalid input
                printMenu();
            }
        }
        return choice;
    }

    /**
     * Handles the user's menu choice.
     * @param choice the menu choice
     */
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                listAllAnimals();
                break;
            case 2:
                makeAllMove();
                break;
            case 3:
                makeAllMakeSound();
                break;
            case 4:
                System.out.println("Exiting Zoo Manager.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    /**
     * Lists all animals in the zoo.
     */
    private void listAllAnimals() {
        System.out.println("\nAnimals in the Zoo:");
        for (Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName());
        }
    }

    /**
     * Makes all animals move.
     */
    private void makeAllMove() {
        System.out.println("\nAll animals move:");
        for (Animal animal : animals) {
            animal.move();
        }
    }

    /**
     * Makes all animals make their sound.
     */
    private void makeAllMakeSound() {
        System.out.println("\nAll animals make sound:");
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}

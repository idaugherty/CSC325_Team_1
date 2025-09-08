package main;
import animals.Animal;
import animals.Bird;
import animals.Fish;
import animals.Mammal;
import main.ZooManager;

/**
 * The entry point for the Zoo application.
 * <p>
 * This class initializes the ZooManager, loads animals, and starts the interactive menu.
 */
public class Main {
    /**
     * Main method to start the Zoo application.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create ZooManager and load animals
        ZooManager zooManager = new ZooManager();
        zooManager.loadAnimals();
        zooManager.displayMenu();
    }
}

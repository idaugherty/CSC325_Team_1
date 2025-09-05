import java.util.ArrayList;

// Add these imports if your classes are in a package, e.g.:
// import yourpackage.Animal;
// import yourpackage.Bird;
// import yourpackage.Fish;
// import yourpackage.Mammal;
// import yourpackage.Flyable;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Bird("Robin"));
        animals.add(new Fish("Goldfish"));
        animals.add(new Mammal("Tiger"));

        for (Animal a : animals) {
            System.out.println("Name: " + a.getName());
            System.out.println("Movement: " + a.move());
            System.out.println("Sound: " + a.makeSound());
            if (a instanceof Flyable) {
                System.out.println("Flying: " + ((Flyable)a).fly());
            }
            if (a instanceof Walkable) {
                System.out.println("Walking: " + ((Walkable)a).walk());
            }
            System.out.println();
        }
    }
}
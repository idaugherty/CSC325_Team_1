package animals;
/*key word for inheritance is "extends"
    Bird is a subclass of Animal
    Animal is a superclass of Bird
*/

import interfaces.Flyable;
import interfaces.Walkable;

public class Bird extends Animal implements Flyable, Walkable {
    private String species;

    // Constructor sets the species
    public Bird(String species) {
        this.species = species;
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp Chirp");
    }

    @Override
    public void move() {
        System.out.println("Flying");
    }

    @Override
    public void fly() {
        System.out.println("The bird is flying.");
    }

    @Override
    public void walk() {
        System.out.println("The bird is walking.");
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}

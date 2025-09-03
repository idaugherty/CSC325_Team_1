package animals;
/*key word for inheritance is "extends"
Mammal is a subclass of Animal
Animal is a superclass of Mammal
*/

import interfaces.Walkable;

public class Mammal extends Animal implements Walkable {
    public String name;

    // this a construcor of the class Mammal setting the name of the mammal
    public Mammal(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println("Roar Roar");
    }

    @Override
    public void move() {
        System.out.println("Running");
    }

    @Override
    public void walk() {
        System.out.println("The mammal is walking.");
    }
}

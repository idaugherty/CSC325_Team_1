package animals;
/*key word for inheritance is "extends"
Fish is a subclass of Animal
Animal is a superclass of Fish
*/

public class Fish extends Animal {

    public String name;

    // this a construcor of the class Fish setting the name of the fish
    public Fish(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println("Blub Blub");
    }

    @Override
    public void move() {
        System.out.println("Swimming");
    }

}

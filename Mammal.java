public class Mammal extends Animal implements Walkable {
    public Mammal(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Growl";
    }

    @Override
    public String move() {
        return "Runs";
    }

    @Override
    public String walk() {
        return "Walks on four legs";
    }
}
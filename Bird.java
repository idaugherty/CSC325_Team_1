public class Bird extends Animal implements Flyable, Walkable {
    public Bird(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Chirp";
    }

    @Override
    public String move() {
        return "Hops";
    }

    @Override
    public String fly() {
        return "Flies in the sky";
    }

    @Override
    public String walk() {
        return "Walks on two legs";
    }
}
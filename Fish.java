public class Fish extends Animal {
    public Fish(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Blub";
    }

    @Override
    public String move() {
        return "Swims";
    }
}
public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String color, String name, double base, double height) {
        super(color, name);
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " triangle named " +
            getName() + " with base " + base + " and height " + height);
    }

    public String describe() {
        return "Shape: " + getName() + ", Color: " + getColor() + ", Sides: 3";
    }
}
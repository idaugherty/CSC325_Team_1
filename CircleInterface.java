public class CircleInterface implements Drawable, Calculable, Describable {
    private String color;
    private String name;
    private double radius;

    public CircleInterface(String color, String name, double radius) {
        this.color = color;
        this.name = name;
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle named " + name +
            " with radius " + radius);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String describe() {
        return "Shape: " + name + ", Color: " + color + ", Sides: 0";
    }
}
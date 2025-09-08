public class Circle2 implements Calculable, Drawable, Describable {
    private double radius;
    private String color;
    private String name;

    public Circle2(double radius, String color, String name) {
        this.radius = radius;
        this.color = color;
        this.name = name;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle named " + name + " with radius " + radius);
    }

    @Override
    public void describe() {
        System.out.println("This is a " + color + " circle named " + name + " with radius " + radius);
    }
    
}

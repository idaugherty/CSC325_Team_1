/**
 * Circle1 represents a circle shape with a radius, color, and name.
 * It extends Shape and provides area and drawing functionality.
 */
public class Circle1 extends Shape {
    private double radius;

    public Circle1(double radius, String color, String name) {
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
}

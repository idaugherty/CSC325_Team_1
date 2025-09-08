/**
 * Rectangle1 represents a rectangle shape with length and width.
 * It extends Shape and provides area and drawing functionality.
 */
public class Rectangle1 extends Shape {
    private double length;
    private double width;

    public Rectangle1(double length, double width, String color, String name) {
        this.length = length;
        this.width = width;
        this.color = color;
        this.name = name;
    }
    
    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle named " + name + " with length " + length + " and width " + width);
    }
}

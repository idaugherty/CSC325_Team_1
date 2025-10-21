    /**
     * Hexagon represents a hexagonal shape with a given side length, color, and name.
     * Implements Calculable, Drawable, and Describable interfaces.
     */
    public class Hexagon extends Shape implements Calculable, Drawable, Describable  {
    private double sideLength;
    private String color;
    private String name;

    public Hexagon(double sideLength, String color, String name) {
        this.sideLength = sideLength;
        this.color = color;
        this.name = name;
    }

    @Override
    public double getArea() {
        return (3 * Math.sqrt(3) / 2) * sideLength * sideLength;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " hexagon named " + name + " with side length " + sideLength);
    }

    @Override
    public void describe() {
        System.out.println("This is a hexagon named " + name + " with side length " + sideLength + " and color " + color);
    }
    
}

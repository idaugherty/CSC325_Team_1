public abstract class Shape {
    /**
     * Shape is an abstract base class for geometric shapes.
     * It defines color, name, and abstract methods for area and drawing.
     */
    String color;
    String name;

    public abstract double getArea();

    public abstract void draw();

}

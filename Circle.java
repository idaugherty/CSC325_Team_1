public class Circle extends Shape {
    private double radius;

    public Circle(String color, String name, double radius) {
        super(color, name);
        this.radius = radius;
    }
    
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " circle named " + getName() + " with radius " + radius);
    }

}

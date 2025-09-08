public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, String name, double width, double height) {
        super(color, name);
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + getColor() + " rectangle named " 
        + getName() + " with width " + width + " and height " + height);
    }

}

public class Rectangle2  implements Calculable, Drawable,  Describable {
    private double length;
    private double width;
    private String color;
    private String name;

    public Rectangle2(double length, double width, String color, String name) {
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
    
    @Override
    public void describe() {
        System.out.println("This is a " + color + " rectangle named " + name + " with length " + length + " and width " + width);
    }
}

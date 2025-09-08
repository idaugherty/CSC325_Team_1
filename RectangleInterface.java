public class RectangleInterface implements Drawable, Calculable, Describable {
    private String color;
    private String name;
    private double width;
    private double height;

    public RectangleInterface(String color, String name, double width, double height) {
        this.color = color;
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle named " + name +
            " with width " + width + " and height " + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String describe() {
        return "Shape: " + name + ", Color: " + color + ", Sides: 4";
    }
}
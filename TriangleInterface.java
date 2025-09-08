public class TriangleInterface implements Drawable, Calculable, Describable {
    private String color;
    private String name;
    private double base;
    private double height;

    public TriangleInterface(String color, String name, double base, double height) {
        this.color = color;
        this.name = name;
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " triangle named " + name +
            " with base " + base + " and height " + height);
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public String describe() {
        return "Shape: " + name + ", Color: " + color + ", Sides: 3";
    }
}
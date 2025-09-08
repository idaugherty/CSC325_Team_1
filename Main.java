public class Main {
    public static void main(String[] args) {
        // Create a rectangle
        Rectangle rectangle = new Rectangle("Blue", "Rectangle", 4.0, 6.0);
        rectangle.draw();
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Description: " + rectangle.getName() + ", Sides: 4");

        System.out.println();

        // Create a circle (assuming you have a Circle class)
        Circle circle = new Circle("Red", "Circle", 5.0);
        circle.draw();
        System.out.println("Area: " + circle.getArea());
        System.out.println("Description: " + circle.getName() + ", Sides: 0");
    }
}



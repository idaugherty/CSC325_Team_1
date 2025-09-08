public class Main {
    public static void main(String[] args) {
        // Using interface-based Rectangle
        RectangleInterface rectangle = new RectangleInterface("Blue", "Rectangle", 4.0, 6.0);
        rectangle.draw();
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Description: " + rectangle.describe());

        System.out.println();

        // Using interface-based Circle
        CircleInterface circle = new CircleInterface("Red", "Circle", 5.0);
        circle.draw();
        System.out.println("Area: " + circle.getArea());
        System.out.println("Description: " + circle.describe());
    }
}



public class Main {
    public static void main(String[] args) {
        // Polymorphic array using interfaces
        Drawable[] shapes = {
            new RectangleInterface("Blue", "Rectangle", 4.0, 6.0),
            new CircleInterface("Red", "Circle", 5.0),
            new TriangleInterface("Green", "Triangle", 3.0, 4.0)
        };

        for (Drawable shape : shapes) {
            shape.draw();
            // Cast to Calculable and Describable to access shared behavior
            System.out.println("Area: " + ((Calculable) shape).getArea());
            System.out.println("Description: " + ((Describable) shape).describe());
            System.out.println();
        }

        // Abstract class version
        Shape[] absShapes = {
            new Rectangle("Blue", "Rectangle", 4.0, 6.0),
            new Circle("Red", "Circle", 5.0),
            new Triangle("Green", "Triangle", 3.0, 4.0)
        };

        for (Shape shape : absShapes) {
            shape.draw();
            System.out.println("Area: " + shape.getArea());
            // For description, call describe() if implemented
            if (shape instanceof Triangle) {
                System.out.println(((Triangle) shape).describe());
            } else if (shape instanceof Rectangle) {
                System.out.println("Shape: " + shape.getName() + ", Color: " + shape.getColor() + ", Sides: 4");
            } else if (shape instanceof Circle) {
                System.out.println("Shape: " + shape.getName() + ", Color: " + shape.getColor() + ", Sides: 0");
            }
            System.out.println();
        }
    }
}



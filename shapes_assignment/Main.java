public class Main {
    public static void main(String[] args) {

        Hexagon hex = new Hexagon(5.0, "blue", "Hexy");
        hex.draw();
        hex.describe();
        System.out.println("Area: " + hex.getArea());

        Rectangle1 rect1 = new Rectangle1(10.0, 100.0, "red", "Recty");
        rect1.draw();
        System.out.println("Area: " + rect1.getArea());

        Rectangle2 rect2 = new Rectangle2(5.0, 2.0, "blue", "Hexy");
        rect2.draw();
        rect2.describe();
        System.out.println("Area: " + rect2.getArea());

        Circle1 circle1 = new Circle1(7.0, "green", "Circy");
        circle1.draw();
        System.out.println("Area: " + circle1.getArea());

        Circle2 circle2 = new Circle2(3.0, "yellow", "Sunny");
        circle2.draw();
        circle2.describe();
        System.out.println("Area: " + circle2.getArea());

    }
}

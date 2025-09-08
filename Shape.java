public abstract class Shape {
    private String color;
    private String name;

    public Shape(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /**
     * Calculates the area of the shape.
     * @return area
     */
    public abstract double getArea();

    /**
     * Draws the shape (prints description).
     */
    public abstract void draw();

}

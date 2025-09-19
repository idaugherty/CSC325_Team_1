package edu.murraystate.demogui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape implements DrawableShape {
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final Color color;

    public RectangleShape(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getDescription() {
        return String.format("Rectangle (x=%.1f y=%.1f w=%.1f h=%.1f area=%.2f)",
                x, y, width, height, getArea());
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public boolean contains(double x, double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

}

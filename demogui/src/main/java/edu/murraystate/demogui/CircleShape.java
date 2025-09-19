package edu.murraystate.demogui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements DrawableShape {
    private final double x;
    private final double y;
    private final double radius;
    private final Color color;

    public CircleShape(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

/**
 * CircleShape.java
 *
 * A drawable circle implementation of {@link DrawableShape}.
 * <p>
 * Encapsulates the circle's position, radius, and color,
 * and provides methods for drawing, description, and area calculation.
 * </p>
 *
 * Drawn on a {@link javafx.scene.canvas.Canvas} via its
 * {@link javafx.scene.canvas.GraphicsContext}.
 *
 * @author Bella Daugherty
 * @version 1.0
 * @since 2025-09-19
 */

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

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getDescription() {
        return String.format("Circle (x=%.1f y=%.1f r=%.1f area=%.2f)", x, y, radius, getArea());
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
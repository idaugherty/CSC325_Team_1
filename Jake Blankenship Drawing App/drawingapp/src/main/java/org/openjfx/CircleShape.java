package org.openjfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements DrawableShape {
    private final double centerX, centerY, radius;

    public CircleShape(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = centerX - radius;
        double y = centerY - radius;
        gc.setFill(Color.PINK);
        gc.fillOval(x, y, radius * 2, radius * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x, y, radius * 2, radius * 2);
    }
}


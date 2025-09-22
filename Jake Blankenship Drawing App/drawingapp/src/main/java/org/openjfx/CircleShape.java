package org.openjfx;

import javafx.scene.canvas.GraphicsContext;

public class CircleShape extends DrawableShape {
    private final double radius;

    public CircleShape(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}






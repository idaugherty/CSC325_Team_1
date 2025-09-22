package org.openjfx;

import javafx.scene.canvas.GraphicsContext;

public abstract class DrawableShape {
    protected double x;
    protected double y;

    public DrawableShape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(GraphicsContext gc);
}







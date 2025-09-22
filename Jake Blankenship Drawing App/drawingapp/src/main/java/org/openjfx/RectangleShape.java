package org.openjfx;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape extends DrawableShape {
    private final double width;
    private final double height;

    public RectangleShape(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // draw centered on the click point
        double topLeftX = x - width / 2;
        double topLeftY = y - height / 2;
        gc.strokeRect(topLeftX, topLeftY, width, height);
    }
}





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

}

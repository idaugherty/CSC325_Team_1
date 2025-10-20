package edu.murraystate.demogui;

import javafx.scene.canvas.GraphicsContext;

public interface DrawableShape {

    void draw(GraphicsContext graphicsContext);

    double getArea();

     String getDescription();

    // Provide the shape's approximate anchor point for labeling (center x/y)
    double getX();

    double getY();

    // Hit-test: whether the point (x,y) is inside this shape (for selection/tooltip)
    boolean contains(double x, double y);
}

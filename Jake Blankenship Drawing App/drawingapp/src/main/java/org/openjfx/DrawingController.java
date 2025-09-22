package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingController {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final ObservableList<DrawableShape> shapes;

    public DrawingController(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.shapes = FXCollections.observableArrayList();
    }

    public void addShape(String type, double x, double y) {
        DrawableShape shape;
        if ("Rectangle".equals(type)) {
            shape = new RectangleShape(x, y, 80, 50);
        } else if ("Circle".equals(type)) {
            shape = new CircleShape(x, y, 30);
        } else {
            return;
        }
        shapes.add(shape);
        redraw();
    }

    // <-- this must exist!
    public void clear() {
        shapes.clear();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void redraw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableShape s : shapes) {
            s.draw(gc);
        }
    }
}

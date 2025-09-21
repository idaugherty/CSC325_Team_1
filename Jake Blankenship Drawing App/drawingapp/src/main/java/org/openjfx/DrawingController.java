package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class DrawingController {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();
    private String currentShape = "Rectangle";

    public DrawingController(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        setupMouseHandler();
    }

    public void setCurrentShape(String shapeType) {
        this.currentShape = shapeType;
    }

    public void clearCanvas() {
        shapes.clear();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void setupMouseHandler() {
        canvas.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        DrawableShape shape;
        if (currentShape.equals("Rectangle")) {
            shape = new RectangleShape(x, y, 60, 40);
        } else {
            shape = new CircleShape(x, y, 30);
        }

        shapes.add(shape);
        redrawCanvas();
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableShape shape : shapes) {
            shape.draw(gc);
        }
    }
}




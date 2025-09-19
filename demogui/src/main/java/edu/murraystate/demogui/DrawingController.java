package edu.murraystate.demogui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

// Add this import if CircleShape is in the same package or adjust the package as needed
import edu.murraystate.demogui.CircleShape;

public class DrawingController {

    private final GraphicsContext graphicsContext;
    private final Canvas canvas;
    private final BorderPane root;
    private ToggleGroup shapeToggleGroup;
    private final ObservableList<DrawableShape> shapes;

    public DrawingController(GraphicsContext graphicsContext, Canvas canvas, BorderPane root,
            ObservableList<DrawableShape> shapes) {
        this.graphicsContext = graphicsContext;
        this.canvas = canvas;
        this.root = root;
        this.shapes = shapes;
    }

    public void setupControls() {
        shapeToggleGroup = new ToggleGroup();
        RadioButton circleButton = new RadioButton("Circle");
        RadioButton rectangleButton = new RadioButton("Rectangle");
        circleButton.setToggleGroup(shapeToggleGroup);
        rectangleButton.setToggleGroup(shapeToggleGroup);
        circleButton.setSelected(true);

        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearCanvas());

        HBox controls = new HBox(10, circleButton, rectangleButton, clearButton);
        controls.setPadding(new Insets(10));
        controls.setStyle("-fx-background-color: #ddd;");

        root.setTop(controls);
        root.setCenter(canvas);
    }

    public void setupCanvasClick() {
        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            RadioButton selected = (RadioButton) shapeToggleGroup.getSelectedToggle();
            DrawableShape newShape = null;

            if (selected.getText().equals("Circle")) {
                newShape = new CircleShape(x, y, 30.0, Color.CORNFLOWERBLUE);
            } else if (selected.getText().equals("Rectangle")) {
                newShape = new RectangleShape(x, y, 60, 40, Color.SALMON);
            }

            if (newShape != null) {
                shapes.add(newShape);
                redrawCanvas();
            }
        });
    }

    private void redrawCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableShape shape : shapes) {
            shape.draw(graphicsContext);
        }
    }

    /**
     * Convenience method to initialize the controller after construction.
     */
    public void initialize() {
        setupControls();
        setupCanvasClick();
        redrawCanvas();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
    }

}
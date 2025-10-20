/**
 * DrawingController.java
 *
 * Controller class responsible for managing the drawing logic and UI layout.
 * <p>
 * Provides event handling for mouse clicks, keyboard shortcuts, and button actions.
 * Keeps track of drawn shapes in an {@link javafx.collections.ObservableList},
 * and updates labels, tooltips, and a {@link javafx.scene.control.ListView}
 * with shape information in real time.
 * </p>
 *
 * Features:
 * <ul>
 *   <li>Draw circles and rectangles on a {@link javafx.scene.canvas.Canvas}</li>
 *   <li>Display shape count and total area</li>
 *   <li>Clear canvas and manage shape list</li>
 *   <li>Keyboard shortcuts for common actions</li>
 * </ul>
 *
 * @author Bella Daugherty
 * @version 1.0
 * @since 2025-09-19
 */

package edu.murraystate.demogui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DrawingController {

    private final GraphicsContext graphicsContext;
    private final Canvas canvas;
    private final BorderPane root;
    private ToggleGroup shapeToggleGroup;
    private final ObservableList<DrawableShape> shapes;
    private ColorPicker colorPicker;
    private CheckBox showLabelsCheckBox;
    private CheckBox showCircleLabelsCheckBox;
    private CheckBox showRectangleLabelsCheckBox;
    private Label circleAreaLabel;
    private Label rectangleAreaLabel;

    // UI controls for info
    private final Label countLabel;
    private final Label totalAreaLabel;
    private final ListView<DrawableShape> listView;
    private final Label selectedLabel;
    private final Tooltip canvasTooltip;

    public DrawingController() {
        root = new BorderPane();

        // Canvas setup
        canvas = new Canvas(800, 600);
        graphicsContext = canvas.getGraphicsContext2D();

        shapes = FXCollections.observableArrayList();

        // Info UI
        countLabel = new Label("Count: 0");
        totalAreaLabel = new Label("Total area: 0.00");
        listView = new ListView<>(shapes);
        selectedLabel = new Label("Selected: none");
        canvasTooltip = new Tooltip("No selection");
        Tooltip.install(canvas, canvasTooltip);

        setupControls();
        setupInfoPanel();
        setupCanvasClick();
        redrawCanvas();
    }

    /**
     * Constructor used by DrawingApp when it creates its own
     * Canvas/GraphicsContext.
     */
    public DrawingController(GraphicsContext gc, Canvas canvas, BorderPane root, ObservableList<DrawableShape> shapes) {
        this.graphicsContext = gc;
        this.canvas = canvas;
        this.root = root;
        this.shapes = shapes;

        // Info UI
        countLabel = new Label("Count: 0");
        totalAreaLabel = new Label("Total area: 0.00");
        listView = new ListView<>(shapes);
        selectedLabel = new Label("Selected: none");
        canvasTooltip = new Tooltip("No selection");
        Tooltip.install(canvas, canvasTooltip);

        setupControls();
        setupInfoPanel();
        setupCanvasClick();
        redrawCanvas();
    }

    public void setupControls() {
        // Radio buttons for shape selection
        shapeToggleGroup = new ToggleGroup();
        RadioButton circleButton = new RadioButton("Circle");
        RadioButton rectangleButton = new RadioButton("Rectangle");
        circleButton.setToggleGroup(shapeToggleGroup);
        rectangleButton.setToggleGroup(shapeToggleGroup);
        circleButton.setSelected(true);

        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearCanvas());

        // ColorPicker (optional, small UX improvement)
        this.colorPicker = new ColorPicker(Color.CORNFLOWERBLUE);

        // Layout top controls
        HBox controls = new HBox(10, circleButton, rectangleButton, new Label("Color:"), colorPicker, clearButton);
        controls.setPadding(new Insets(8));
        controls.setStyle("-fx-background-color: #efefef;");

        // Canvas click handling is set up in setupCanvasClick() so DrawingApp can
        // provide its own canvas/graphics context via the alternate constructor.

        root.setTop(controls);
        root.setCenter(canvas);

        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case C -> {
                    if (event.isShiftDown()) {
                        // Shift+C = Clear canvas
                        clearCanvas();
                    } else {
                        // C = Circle
                        shapeToggleGroup.selectToggle(circleButton);
                    }
                }
                case R -> shapeToggleGroup.selectToggle(rectangleButton);
                case O -> shapeToggleGroup.selectToggle(circleButton);
            }
        });
    }

    private void setupInfoPanel() {
        // Bind count label to shapes size
        countLabel.textProperty().bind(Bindings.createStringBinding(
                () -> "Count: " + shapes.size(),
                shapes));

        // Create a DoubleBinding for total area and bind totalAreaLabel to it
        // (formatted)
        DoubleBinding totalAreaBinding = Bindings.createDoubleBinding(
                () -> Double.valueOf(shapes.stream().mapToDouble(DrawableShape::getArea).sum()),
                shapes);

        totalAreaLabel.textProperty().bind(Bindings.format("Total area: %.2f", totalAreaBinding));

        // Per-shape area labels and selectors
        showLabelsCheckBox = new CheckBox("Show labels");
        showLabelsCheckBox.setSelected(true);
        showCircleLabelsCheckBox = new CheckBox("Circle labels");
        showCircleLabelsCheckBox.setSelected(true);
        showRectangleLabelsCheckBox = new CheckBox("Rectangle labels");
        showRectangleLabelsCheckBox.setSelected(true);

        circleAreaLabel = new Label("Circles total area: 0.00");
        rectangleAreaLabel = new Label("Rectangles total area: 0.00");

        // When toggles change, update canvas and labels
        showLabelsCheckBox.setOnAction(e -> redrawCanvas());
        showCircleLabelsCheckBox.setOnAction(e -> redrawCanvas());
        showRectangleLabelsCheckBox.setOnAction(e -> redrawCanvas());

        // Update per-shape area labels when shape list changes
        shapes.addListener((javafx.collections.ListChangeListener<DrawableShape>) change -> {
            updatePerShapeAreaLabels();
        });

        // initialize per-shape labels
        updatePerShapeAreaLabels();

        // ListView: show descriptions
        listView.setCellFactory(lv -> new ListCell<DrawableShape>() {
            @Override
            protected void updateItem(DrawableShape item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getDescription());
            }
        });

        // ListView selection -> update selected label and canvas tooltip
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel == null) {
                selectedLabel.setText("Selected: none");
                canvasTooltip.setText("No selection");
            } else {
                selectedLabel.setText("Selected: " + newSel.getDescription());
                canvasTooltip.setText(newSel.getDescription());
            }
        });

        // Right-side VBox for info
        VBox infoBox = new VBox(8,
                countLabel,
                totalAreaLabel,
                new HBox(8, showLabelsCheckBox, showCircleLabelsCheckBox, showRectangleLabelsCheckBox),
                circleAreaLabel,
                rectangleAreaLabel,
                new Label("Shapes:"), listView, selectedLabel);
        infoBox.setPadding(new Insets(8));
        infoBox.setPrefWidth(300);
        VBox.setVgrow(listView, Priority.ALWAYS);

        root.setRight(infoBox);
    }

    /**
     * Redraws canvas and draws a small label for each shape showing description and
     * area.
     */
    private void redrawCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableShape shape : shapes) {
            shape.draw(graphicsContext);
            // no per-shape labels anymore; category labels drawn after shapes
        }

        // Draw one label per category (controlled by checkboxes)
        boolean showLabels = showLabelsCheckBox == null || showLabelsCheckBox.isSelected();
        boolean showCircleLabels = showCircleLabelsCheckBox == null || showCircleLabelsCheckBox.isSelected();
        boolean showRectangleLabels = showRectangleLabelsCheckBox == null || showRectangleLabelsCheckBox.isSelected();

        double labelX = 10;
        double labelY = 20;
        double lineHeight = 18;

        if (showLabels) {
            if (showCircleLabels) {
                String circleLabel = String.format("Circles — total area: %.2f", getTotalAreaForCircles());
                graphicsContext.setFill(Color.rgb(255, 255, 255, 0.8));
                double textWidth = circleLabel.length() * 7;
                double textHeight = 14;
                graphicsContext.fillRect(labelX - 4, labelY - textHeight + 4, textWidth + 8, textHeight + 6);
                graphicsContext.setFill(Color.BLACK);
                graphicsContext.fillText(circleLabel, labelX, labelY);
                labelY += lineHeight;
            }
            if (showRectangleLabels) {
                String rectLabel = String.format("Rectangles — total area: %.2f", getTotalAreaForRectangles());
                graphicsContext.setFill(Color.rgb(255, 255, 255, 0.8));
                double textWidth = rectLabel.length() * 7;
                double textHeight = 14;
                graphicsContext.fillRect(labelX - 4, labelY - textHeight + 4, textWidth + 8, textHeight + 6);
                graphicsContext.setFill(Color.BLACK);
                graphicsContext.fillText(rectLabel, labelX, labelY);
            }
        }
    }

    private void updatePerShapeAreaLabels() {
        double circleArea = shapes.stream()
                .filter(s -> s instanceof CircleShape)
                .mapToDouble(DrawableShape::getArea)
                .sum();

        double rectArea = shapes.stream()
                .filter(s -> s instanceof RectangleShape)
                .mapToDouble(DrawableShape::getArea)
                .sum();

        if (circleAreaLabel != null) {
            String desc = getDescriptionsForCircles(5);
            circleAreaLabel.setText(
                    String.format("Circles total area: %.2f%s", circleArea, desc.isEmpty() ? "" : " — " + desc));
        }
        if (rectangleAreaLabel != null) {
            String desc = getDescriptionsForRectangles(5);
            rectangleAreaLabel.setText(
                    String.format("Rectangles total area: %.2f%s", rectArea, desc.isEmpty() ? "" : " — " + desc));
        }
    }

    private String getDescriptionsForCircles(int max) {
        return "";
    }

    private String getDescriptionsForRectangles(int max) {
        return "";
    }

    private double getTotalAreaForCircles() {
        return shapes.stream()
                .filter(s -> s instanceof CircleShape)
                .mapToDouble(DrawableShape::getArea)
                .sum();
    }

    private double getTotalAreaForRectangles() {
        return shapes.stream()
                .filter(s -> s instanceof RectangleShape)
                .mapToDouble(DrawableShape::getArea)
                .sum();
    }

    /**
     * Set up canvas click handling (separated so DrawingApp can supply its own
     * canvas)
     */
    private void setupCanvasClick() {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double x = event.getX();
            double y = event.getY();

            RadioButton selected = (RadioButton) shapeToggleGroup.getSelectedToggle();
            DrawableShape newShape = null;

            if (selected != null && "Circle".equals(selected.getText())) {
                newShape = new CircleShape(x, y, 30, colorPicker.getValue());
            } else if (selected != null && "Rectangle".equals(selected.getText())) {
                newShape = new RectangleShape(x, y, 60, 40, colorPicker.getValue());
            }

            if (newShape != null) {
                shapes.add(newShape);
                redrawCanvas();
                // optionally scroll ListView to new item
                listView.scrollTo(newShape);
                listView.getSelectionModel().select(newShape);
            }
        });
    }

    /**
     * Convenience method to initialize the controller after construction.
     */
    public void initialize() {
        setupControls();
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
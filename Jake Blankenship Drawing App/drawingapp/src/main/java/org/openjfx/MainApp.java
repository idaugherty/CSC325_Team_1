package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create canvas and controller
        Canvas canvas = new Canvas(600, 400);
        DrawingController controller = new DrawingController(canvas);

        // Shape selection
        RadioButton rectBtn = new RadioButton("Rectangle");
        RadioButton circleBtn = new RadioButton("Circle");
        ToggleGroup shapeToggle = new ToggleGroup();
        rectBtn.setToggleGroup(shapeToggle);
        circleBtn.setToggleGroup(shapeToggle);
        rectBtn.setSelected(true);  // default

        // Bind toggle to controller
        shapeToggle.selectedToggleProperty().addListener((obs, old, newVal) -> {
            if (newVal == rectBtn) controller.setCurrentShape("Rectangle");
            else if (newVal == circleBtn) controller.setCurrentShape("Circle");
        });

        // Clear button
        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> controller.clearCanvas());

        // Layout
        HBox controls = new HBox(10, rectBtn, circleBtn, clearBtn);
        controls.setStyle("-fx-padding: 10;");

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(canvas);

        // Scene and stage
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Drawing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(700, 500);

        ToggleGroup shapeGroup = new ToggleGroup();
        RadioButton rectBtn = new RadioButton("Rectangle");
        rectBtn.setToggleGroup(shapeGroup);
        rectBtn.setSelected(true);
        RadioButton circleBtn = new RadioButton("Circle");
        circleBtn.setToggleGroup(shapeGroup);

        HBox controls = new HBox(10, rectBtn, circleBtn);
        Button clearBtn = new Button("Clear");

        VBox leftPanel = new VBox(12, controls, clearBtn);
        leftPanel.setStyle("-fx-padding:10;");

        BorderPane root = new BorderPane();
        root.setLeft(leftPanel);
        root.setCenter(canvas);

        // <-- Use the single-Canvas constructor
        DrawingController controller = new DrawingController(canvas);

        canvas.setOnMouseClicked(e -> {
            RadioButton selected = (RadioButton) shapeGroup.getSelectedToggle();
            String shapeType = selected.getText();
            controller.addShape(shapeType, e.getX(), e.getY());
        });

        clearBtn.setOnAction(e -> controller.clear());

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Drawing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

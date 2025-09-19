package edu.murraystate.demogui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DrawingApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // create canvas and graphics context
        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(800, 600);
        javafx.scene.canvas.GraphicsContext gc = canvas.getGraphicsContext2D();
        javafx.collections.ObservableList<DrawableShape> shapes = javafx.collections.FXCollections.observableArrayList();

        BorderPane rootPane = new BorderPane();
        DrawingController controller = new DrawingController(gc, canvas, rootPane, shapes);
        controller.initialize();

        BorderPane root = controller.getRoot();

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Drawing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
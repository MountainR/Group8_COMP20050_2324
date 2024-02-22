package com.example.javafx_0216;    // package name

import javafx.application.Application;  // the main class for JavaFX applications
import javafx.fxml.FXMLLoader;          //  loading FXML files
import javafx.scene.Scene;              //  for all content in a scene graph
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;              // the top-level container for a JavaFX application

import java.io.IOException;

public class HelloApplication extends Application {
    // defines a class named HelloApplication that extends the Application class, indicating that it is a JavaFX application.
    @Override
    public void start(Stage stage) throws IOException {
        // The start method is the entry point for a JavaFX application.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        StackPane rootPane = new StackPane();
        Pane fxml = fxmlLoader.load();
        // ---------------- I want to get coordinates from this fxml -----------------
//        double [61][2] coordinates =


//        Pane circles = addCirclePane();
        rootPane.getChildren().addAll(fxml);

        Scene scene = new Scene(rootPane, 800, 800);


        // These lines set the title of the stage, set the scene to be displayed on the stage, and then make the stage visible.
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public Pane addCirclePane() {
        Pane atoms = new Pane();
        // ------- central coordinates------
        double[][] centralCor = {
                {350, 20},


        };

        // generate hexagons by coordinates
        for (double[] center : centralCor) {
            Circle atom = new Circle(center[0], center[1], 20);

            // add hexagons to root
            atom.setFill(Color.AQUA);
            atoms.getChildren().add(atom);
        }
        return atoms;
    }
    public static void main(String[] args) {
        // the entry point of the program
        launch();
        // launch() method, which is a static method provided by the Application class. This method initializes the JavaFX system and calls the start method to start the application
    }
}
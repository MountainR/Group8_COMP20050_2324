package com.example.app; // package name
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
/*
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
import java.util.ArrayList;

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


        Pane circles = addCirclePane();
        rootPane.getChildren().addAll(fxml, circles);

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
                {10, 478},
                {10, 10},
                {10, 118},

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
      //  launch();
        // launch() method, which is a static method provided by the Application class. This method initializes the JavaFX system and calls the start method to start the application
        ArrayList<String> atoms = new ArrayList<>();

        //9 rows
        int max = 4;
        int k = 0;
      //  for(int k = 0; k < 62; k++) {
        while(k < 62) {
            for (int i = 1; i < 10; i++) {
                if (i < 6) {
                    max++;
                } else {
                    max--;
                }
                //varying columns
                for (int j = 1; j < max + 1; j++) {
                    atoms.add("atom%d%d");
                    //atoms.set(k, String.format(atoms.get(k), i));
                    atoms.set(k, String.format(atoms.get(k), i, j));
                    k++;
                }
            }
        }
       // }

        for (String element : atoms) {
            System.out.println(element);
        }

        int size = atoms.size();
        System.out.println(size);

    }
    //arrayList

}
*/
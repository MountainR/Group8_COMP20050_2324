package com.example.javafx_0216;    // package name

import javafx.application.Application;  // the main class for JavaFX applications
import javafx.fxml.FXMLLoader;          //  loading FXML files
import javafx.scene.Scene;              //  for all content in a scene graph
import javafx.stage.Stage;              // the top-level container for a JavaFX application

import java.io.IOException;

public class HelloApplication extends Application {
    // defines a class named HelloApplication that extends the Application class, indicating that it is a JavaFX application.
    @Override
    public void start(Stage stage) throws IOException {
        // The start method is the entry point for a JavaFX application.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        // These lines set the title of the stage, set the scene to be displayed on the stage, and then make the stage visible.
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // the entry point of the program
        launch();
        // launch() method, which is a static method provided by the Application class. This method initializes the JavaFX system and calls the start method to start the application
    }
}
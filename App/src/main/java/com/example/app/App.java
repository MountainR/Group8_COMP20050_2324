package com.example.app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;

/*
public class App extends Application {

            @Override
            public void start(Stage stage) {

                Pane row1 = createRow("0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0");
                Pane row2 = createRow("0,1", "1,1", "2,1", "3,1", "4,1", "5,1");
                Pane row3 = createRow("0,2", "1,2", "2,2", "3,2", "4,2", "5,2", "6,2");
                Pane row4 = createRow("0,3", "1,3", "2,3", "3,3", "4,3", "5,3");

                VBox pane = new VBox(row1, row2, row3, row4);
                pane.setAlignment(Pos.CENTER);
                pane.setStyle("-fx-background-color: #37474f; -fx-padding: 20;");

                Scene scene = new Scene(pane);

                stage.setScene(scene);
                stage.show();
            }

            private static Pane createRow(String... texts) {
                HBox pane = new HBox();
                pane.setAlignment(Pos.CENTER);

                Arrays.stream(texts).map(com.example.app.App.App::createStar)
                        .forEach(pane.getChildren()::add);

                return pane;
            }

            private static Pane createStar(String text) {
                Polygon star = new Polygon(15, 0, 30, 10, 45, 0, 45, 17.32, 60, 25.98, 45, 34.64, 45, 51.96, 30, 43.3, 15, 51.96, 15, 34.64, 0, 25.98, 15, 17.32, 15, 0);

                star.setFill(Color.TRANSPARENT);
                star.setStrokeWidth(2);
                star.setStroke(Color.BLACK);

                // Removes the small gap between rows
                star.setScaleX(1.1);
                star.setScaleY(1.1);

                Label label = new Label(text);
                label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                label.setTextFill(Color.WHITE);

                StackPane pane = new StackPane();
                pane.getChildren().addAll(star, label);

                return pane;
            }

            public static void main(String[] args) {
                launch();
            }
        }
*/



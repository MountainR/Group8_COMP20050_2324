package com.example.demo_javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // just some text
        Pane row1 = createRow("1", "2", "3", "4", "5", "6");
        Pane row2 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row3 = createRow("1", "2", "3", "4", "5", "6", "7", "8");
        Pane row4 = createRow("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Pane row5 = createRow("1", "2", "3", "4", "5", "6", "7", "8");
        Pane row6 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row7 = createRow("1", "2", "3", "4", "5", "6");

        VBox pane = new VBox(row1, row2, row3, row4, row5, row6, row7);
        VBox.setMargin(row1, new Insets(-10, -10, -10, -10));
        VBox.setMargin(row2, new Insets(0, 0, 0, 0));
        VBox.setMargin(row3, new Insets(-10, -10, -10, -10));
        VBox.setMargin(row4, new Insets(0, 0, 0, 0));
        VBox.setMargin(row5, new Insets(-10, -10, -10, -10));
        VBox.setMargin(row6, new Insets(0, 0, 0, 0));
        VBox.setMargin(row7, new Insets(-10, -10, -10, -10));
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    private static Pane createRow(String... texts) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        Arrays.stream(texts).map(App::createStar)
                .forEach(pane.getChildren()::add);

        return pane;
    }

    private static Pane createStar(String text) {
        int halfEdge = 25;
        Polygon star = new Polygon(0, halfEdge, 1.73*halfEdge, 0,
                3.46*halfEdge, halfEdge, 3.46*halfEdge, 3*halfEdge,
                1.73*halfEdge, 4*halfEdge, 0, 3*halfEdge,
                0, halfEdge
                                            );

        star.setFill(Color.TRANSPARENT);
        star.setStrokeWidth(2);
        star.setStroke(Color.BLACK);

        // Removes the small gap between rows
        star.setScaleX(1.04);
        star.setScaleY(1.0);

        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        label.setTextFill(Color.BLACK);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(star, label);

        return pane;
    }

    public static void main(String[] args) {
        launch();
    }
}
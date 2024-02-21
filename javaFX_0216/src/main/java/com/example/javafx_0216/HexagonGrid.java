//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javafx_0216;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HexagonGrid extends Application {
    public HexagonGrid() {
    }

    public void start(Stage stage) {
        // just some text
        Pane row1 = createRow(5);
        Pane row2 = createRow(6);
        Pane row3 = createRow(7);
        Pane row4 = createRow(8);
        Pane row5 = createRow(9);
        Pane row6 = createRow(8);
        Pane row7 = createRow(7);
        Pane row8 = createRow(6);
        Pane row9 = createRow(5);

        VBox pane = new VBox(row1, row2, row3, row4, row5, row6, row7, row8, row9);
        VBox.setMargin(row1, new Insets(-26, -26, -26, -26));
        VBox.setMargin(row2, new Insets(0, 0, 0, 0));
        VBox.setMargin(row3, new Insets(-26, -26, -26, -26));
        VBox.setMargin(row4, new Insets(0, 0, 0, 0));
        VBox.setMargin(row5, new Insets(-26, -26, -26, -26));
        VBox.setMargin(row6, new Insets(0, 0, 0, 0));
        VBox.setMargin(row7, new Insets(-26, -26, -26, -26));
        VBox.setMargin(row8, new Insets(0, 0, 0, 0));
        VBox.setMargin(row9, new Insets(-26, -26, -26, -26));


        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #ffffff; -fx-padding: 100;");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(pane);

        // try to add ints
        for (int i=0; i<5; i++) {
            Label numbers = new Label();
            numbers.setText("" + (i+1));
            anchorPane.getChildren().add(numbers);
            // AnchorPane.setLeftAnchor();
        }



        Scene scene = new Scene(anchorPane);
        // Scene numScene = new Scene(numbers);


        // stage.setScene(numScene);
        stage.setScene(scene);

        stage.show();
    }

    private static Pane createRow(int n) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        for (int i = 0; i < n; i++) {
            pane.getChildren().add(createStar());
        }

        return pane;
    }

    private static Pane createStar() {
        int halfEdge = 22;
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

        StackPane pane = new StackPane();
        pane.getChildren().addAll(star);

        return pane;
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}

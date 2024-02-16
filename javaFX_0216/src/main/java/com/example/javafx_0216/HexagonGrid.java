//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo_javafx;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HexagonGrid extends Application {
    public HexagonGrid() {
    }

    public void start(Stage stage) {
        Pane row1 = createRow("1", "2", "3", "4", "5", "6");
        Pane row2 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row3 = createRow("1", "2", "3", "4", "5", "6", "7", "8");
        Pane row4 = createRow("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Pane row5 = createRow("1", "2", "3", "4", "5", "6", "7", "8");
        Pane row6 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row7 = createRow("1", "2", "3", "4", "5", "6");
        VBox pane = new VBox(new Node[]{row1, row2, row3, row4, row5, row6, row7});
        VBox.setMargin(row1, new Insets(-10.0, -10.0, -10.0, -10.0));
        VBox.setMargin(row2, new Insets(0.0, 0.0, 0.0, 0.0));
        VBox.setMargin(row3, new Insets(-10.0, -10.0, -10.0, -10.0));
        VBox.setMargin(row4, new Insets(0.0, 0.0, 0.0, 0.0));
        VBox.setMargin(row5, new Insets(-10.0, -10.0, -10.0, -10.0));
        VBox.setMargin(row6, new Insets(0.0, 0.0, 0.0, 0.0));
        VBox.setMargin(row7, new Insets(-10.0, -10.0, -10.0, -10.0));
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static Pane createRow(String... texts) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        Stream var10000 = Arrays.stream(texts).map(HexagonGrid::createStar);
        ObservableList var10001 = pane.getChildren();
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::add);
        return pane;
    }

    private static Pane createStar(String text) {
        int halfEdge = 25;
        Polygon star = new Polygon(new double[]{0.0, (double)halfEdge, 1.73 * (double)halfEdge, 0.0, 3.46 * (double)halfEdge, (double)halfEdge, 3.46 * (double)halfEdge, (double)(3 * halfEdge), 1.73 * (double)halfEdge, (double)(4 * halfEdge), 0.0, (double)(3 * halfEdge), 0.0, (double)halfEdge});
        star.setFill(Color.TRANSPARENT);
        star.setStrokeWidth(2.0);
        star.setStroke(Color.BLACK);
        star.setScaleX(1.04);
        star.setScaleY(1.0);
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16.0));
        label.setTextFill(Color.BLACK);
        StackPane pane = new StackPane();
        pane.getChildren().addAll(new Node[]{star, label});
        return pane;
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}

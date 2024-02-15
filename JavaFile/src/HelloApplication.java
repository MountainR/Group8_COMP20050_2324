package test.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        Pane row1 = createRow("1", "2", "3", "4", "5");
        Pane row2 = createRow("1", "2", "3", "4", "5", "6");
        Pane row3 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row4 = createRow("1", "2", "3", "4", "5", "6", "7", "8");
        Pane row5 = createRow("1", "2", "3", "4", "5", "6", "7");
        Pane row6 = createRow("1", "2", "3", "4", "5", "6");
        Pane row7 = createRow("1", "2", "3", "4", "5");

        VBox pane = new VBox(row1, row2, row3, row4, row5, row6, row7);
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #37474f; -fx-padding: 20;");

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    private static Pane createRow(String... texts) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        Arrays.stream(texts).map(HelloApplication::createStar)
                .forEach(pane.getChildren()::add);

        return pane;
    }

    private static Pane createStar(String text) {
        Polygon star = new Polygon(0, 10, 17, 0,
                34, 10, 34, 30,
                17, 40, 0, 30,
                0, 10);

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

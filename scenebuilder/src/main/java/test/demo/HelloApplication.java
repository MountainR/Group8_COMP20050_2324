package test.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // just some text
        Pane row0 = createTextRow(5);
        Pane row1 = createRow(5);
        Pane row2 = createRow(6);
        Pane row3 = createRow(7);
        Pane row4 = createRow(8);
        Pane row5 = createRow(9);
        Pane row6 = createRow(8);
        Pane row7 = createRow(7);
        Pane row8 = createRow(6);
        Pane row9 = createRow(5);


        VBox pane = new VBox(row0, row1, row2, row3, row4, row5, row6, row7, row8, row9);
        VBox.setMargin(row1, new Insets(0, 0, 0, 0));
        VBox.setMargin(row2, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row3, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row4, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row5, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row6, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row7, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row8, new Insets(-26, 0, 0, 0));
        VBox.setMargin(row9, new Insets(-26, 0, 0, 0));


        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #000000; -fx-padding: 20;");

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    private static Pane createRow(int n) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        for (int i = 0; i < n; i++) {
            pane.getChildren().addAll(createStar());
        }

        return pane;
    }

    private static Pane createTextRow(int n) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);

        for (int i = 1; i <= n; i++) {
            Label text = new Label();
            text.setTextFill(Color.WHITE);
            text.setText(String.valueOf(i));
            pane.getChildren().add(text);
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
        star.setStroke(Color.WHITE);

        // Removes the small gap between rows
        star.setScaleX(1.04);
        star.setScaleY(1.0);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(star);

        return pane;
    }

    public static void main(String[] args) {
        launch();
    }
}

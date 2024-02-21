package com.example.javafx_0216;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class HexagonsByCor extends Application {
    // main method
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        // ------ root pane -----------
        Pane root = new Pane();

        // ------- central coordinates------
        double[][] centralCor = {
                {100, 100},
                {200, 200},
                {300, 300}
        };

        // generate hexagons by coordinates
        for (double[] center : centralCor) {
            Polygon hexagon = hexagonGenerator(center[0], center[1]);

            // add hexagons to root
            root.getChildren().add(hexagon);
        }

        // ------ After finishing root, generate scene pane ---------
        Scene scene = new Scene(root, 800, 800);
        // --------- set the scene to stage--------------
        stage.setScene(scene);
        stage.setTitle("Hexagons by Coordinates");
        // --------- show the stage -----------
        stage.show();

    }

    /**
     * Generate a Polygon object by a pair of 2D coordinate
     *
     * @param x central x position
     * @param y central y position
     * @return a Polygon Object
     */
    private Polygon hexagonGenerator(double x, double y) {
        // ------- create an array for 12 points-----------
        // every even index for x, odd index for y
        double[] points;

        // Algorithm for generating 12 points clockwise by center x, y
        double h = 25; // h is half of edge length
        points = new double[]{
                x - h, y + 1.73*h,
                x + h, y + 1.73*h,
                x + 2*h, y,
                x + h, y - 1.73*h,
                x - h, y - 1.73*h,
                x - 2*h, y,
                };



        // ------- Use 12 coordinates to generate a hexagon and return it-------
        Polygon newHexagon = new Polygon(points);
        // ------- make filling transparent -----------
        newHexagon.setFill(Color.TRANSPARENT);
        // ------- make edges visible --------------
        newHexagon.setStrokeWidth(2.0);
        newHexagon.setStroke(Color.BLACK);
        return newHexagon;
    }
}

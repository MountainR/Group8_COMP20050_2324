package JavaFile.src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HelloController {
    @FXML
    private Button startButton;

    @FXML
    private VBox atomContainer;

    @FXML
    private Circle atom1;

    @FXML
    private ArrayList<Circle> atoms;

    //---
    @FXML
    private VBox circleContainer;
    @FXML
    private ArrayList<Circle> circles;
    @FXML
    public void displayAtoms() throws IOException {

    }

    @FXML
    public void initialize() {
        atoms = new ArrayList<Circle>();
        int n = 5;

        /*HBox row = new HBox();
        row.setBorder(Border.stroke(Color.PINK));
        atomContainer.getChildren().add(row);*/

        for (int i = 0; i < 9; i++) {
            HBox row = new HBox();
            row.setAlignment(Pos.TOP_CENTER);
            row.prefHeight(100);
            row.prefWidth(200);
            row.setSpacing(26);
            VBox.setMargin(row, new Insets(7, 0, 7, 0));

            for (int j = 0; j < n; j++) {
                Circle atom = new Circle();
                atom.setFill(Color.PINK);
                atom.setRadius(25);
                atoms.add(atom);
                row.getChildren().add(atom);
            }

            atomContainer.getChildren().add(row);
            if (i < 4) n++;
            else n--;
        }

        circleOfInfluenceDisplay();

    }


    @FXML
    public void circleOfInfluenceDisplay() {
        circles = new ArrayList<Circle>();
        int n = 5;


        for (int i = 0; i < 9; i++) {
            HBox row = new HBox();
            row.setAlignment(Pos.TOP_CENTER);
            row.prefHeight(100);
            row.prefWidth(200);
            row.setSpacing(-75);
            VBox.setMargin(row, new Insets(-43, 0, -43, 0));

            for (int j = 0; j < n; j++) {
                Circle circle = new Circle();
                circle.setFill(Color.TRANSPARENT);
                circle.getStrokeWidth();
                circle.getStrokeDashArray().addAll(5d, 5d);
                circle.setStroke(Color.WHITE);
                circle.setRadius(75);
                circles.add(circle);
                row.getChildren().add(circle);
            }

            circleContainer.getChildren().add(row);
            if (i < 4) n++;
            else n--;
        }

    }

}

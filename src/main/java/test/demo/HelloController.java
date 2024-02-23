package test.demo;

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

    private ArrayList<Circle> atoms;

    private ArrayList<Circle> selectedAtoms = new ArrayList<>();
    private ArrayList<Circle> selectedCircles = new ArrayList<>();

    @FXML
    private VBox circleContainer;
    @FXML
    private ArrayList<Circle> circles;

    /**
     * Displays the atoms that have been randomly selected
     */
    @FXML
    public void displayAtoms() throws IOException {
        for (int i = 0; i < selectedAtoms.size(); i++) {
            selectedAtoms.get(i).setVisible(true); //set all atoms in arraylist to be visible
            selectedCircles.get(i).setVisible(true);
        }
    }

    /**
     * initializes grid with hidden atoms for every hexagon in the grid
     */
    @FXML
    public void initialize() {
        ArrayList<Integer> indexes = new ArrayList<>();
        generateAtoms();
        indexes = RandomlySelect.randomlySelect(atoms, 5);
        for (int i = 0; i < indexes.size(); i++) {
            selectedAtoms.add(atoms.get(indexes.get(i)));
            selectedCircles.add(circles.get(indexes.get(i)));
        }
    }

    /**
     * Generates hidden atoms in each hexagon and adds them to an arraylist
     */
    public void generateAtoms() {
        atoms = new ArrayList<Circle>();
        int n = 5;

        //nested for loop for creation of HBoxes and atoms
        for (int i = 0; i < 9; i++) { //loop for creation of 9 HBoxes
            HBox row = new HBox();
            row.setAlignment(Pos.TOP_CENTER);
            row.prefHeight(100);
            row.prefWidth(200);
            row.setSpacing(26);
            VBox.setMargin(row, new Insets(7, 0, 7, 0));

            for (int j = 0; j < n; j++) { //loop for creation of atoms
                Circle atom = new Circle();
                atom.setFill(Color.PINK);
                atom.setVisible(false);
                atom.setRadius(25);
                atoms.add(atom);
                row.getChildren().add(atom); //adding atoms to HBox
            }

            atomContainer.getChildren().add(row); //adding HBox to VBox
            if (i < 4) n++; //number of atoms increases by 1 if the row is before the middle row
            else n--; //number of atoms decreases by 1 if the row is after the middle row
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
                circle.setVisible(false);
                circles.add(circle);
                row.getChildren().add(circle);
            }

            circleContainer.getChildren().add(row);
            if (i < 4) n++;
            else n--;
        }

    }

}
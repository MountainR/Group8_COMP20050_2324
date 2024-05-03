package test.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class HelloController {
    ArrayList<ArrayList<Line>> lines = new ArrayList<>();
    ArrayList<Line> gaps = new ArrayList<>();

    @FXML
    private Button startButton;

    @FXML private TextField rayField;

    @FXML
    private VBox atomContainer;

    @FXML
    private Pane labelContainer;

    private ArrayList<Circle> atoms;

    private ArrayList<Circle> selectedAtoms = new ArrayList<>();
    private ArrayList<Circle> selectedCirclesOfInfluence = new ArrayList<>();
    private ArrayList<Circle> userSelectedAtoms = new ArrayList<>();

    @FXML
    private VBox circleContainer;
    @FXML
    private ArrayList<Circle> circlesOfInfluence;

    @FXML
    private Pane lineContainer;

    @FXML
    private TextField indexField;

    private int rayCount = 0;

    private double[][] markersArr = new double[54][2];

    public enum directions {southeast, southwest, west, northwest, northeast, east}
    ArrayList<Integer> indexes;
    ArrayList<Integer> userSelectedIndexes = new ArrayList<>();

    @FXML Pane invisibleDots;
    public static Double[][] realCoors = new Double[61][2];
    /**
     * get all real coordinates of hexagons by invisible dots
     */
    @FXML
    public void getRealCoor() {
        ObservableList<Node> dots = invisibleDots.getChildren();
        // get all coordinate
        for(int i=0; i<61; ++i) {
            realCoors[i][0] = dots.get(i).getLayoutX();
            realCoors[i][1] = dots.get(i).getLayoutY();
            //System.out.println("x: "+realCoors[i][0]+" y: "+realCoors[i][1]);
        }

    }

    /**
     * initializes grid with hidden atoms for every hexagon in the grid
     */
    public void initialize() {
        generateAtoms();
        indexes = RandomlySelect.randomlySelect(atoms, 5); //the indexes of the randomly selected atoms are stored in indexes
        for (Integer index : indexes) {
            selectedAtoms.add(atoms.get(index)); //the randomly selected atoms are added to arraylist
            selectedCirclesOfInfluence.add(circlesOfInfluence.get(index)); // the circles of influence are added to an arraylist
        }
        storeCoordinates();
        getRealCoor();
    }

    /**
     * Calculates the ray path
     * @param startLabel the label at which the ray enters
     * @return returns the label at which the ray exits
     */
    @FXML
    public LabelMap calcRay(int startLabel) {
        LabelMap in = new LabelMap(startLabel); //ray entering the board
        SixtyDegreeTest mover = new SixtyDegreeTest(); //calculates the ray path
        ArrayList<SixtyDegreeTest.HexagonCoor> atoms = new ArrayList<>();
        //gets the coordinates of the start of the ray from the index of the start hexagon
        SixtyDegreeTest.HexagonCoor startCoor = mover.getCoorList().get(in.getIndex());

        //the indexes arrayList is the index of each atom that is on the board
        for (Integer index : indexes) {
            atoms.add(mover.getCoorList().get(index)); //add atoms by index
        }

        LabelMap endLabel = mover.moveRay(startCoor, atoms, in.getDirection());
        lines.add(mover.getLine());
        // add a line between label and startCoor
        Line gap1 = getGap1(startCoor, in);
        gaps.add(gap1);
        if (endLabel != null) {
            SixtyDegreeTest.HexagonCoor endCoor = mover.getCoorList().get(endLabel.getIndex());
            Line gap2 = getGap1(endCoor, endLabel);
            gaps.add(gap2);
        }
        return endLabel;
    }

    /**
     * Add a line object to a pane
     */
    @FXML
    public void addLine(ArrayList<Line> lines) {
        for (Line line : lines) {
            invisibleDots.getChildren().add(line);
        }
    }

    /**
     * displays the ray path on the UI
     */
    public void sendRay() {
        int in = Integer.parseInt(rayField.getText()); //takes input from text field
        LabelMap out = calcRay(in);
        if (out == null) {
            setMarker(in, in);
            Label hit = checkLabel(in - 1);
            hit.setText("H");
        }
        else if (out.getLabelNum() == in) {
            setMarker(in, in);
            Label reflect = checkLabel(in - 1);
            reflect.setText("R");
        }
        else {setMarker(in, out.getLabelNum());}
        rayCount++;
    }

    @FXML
    public void storeCoordinates()
    {
        for (int i = 0; i < 54; i++)
        {
            Label label = checkLabel(i);
            markersArr[i][0] = label.getLayoutX();
            markersArr[i][1] = label.getLayoutY();
        }
    }

    public void userSelectAtoms()
    {
        try {
            int in = Integer.parseInt(indexField.getText());

            if (in < 0 || in > 60)
                throw new IllegalArgumentException("Input must be an integer between 0 and 60.");

            if (userSelectedIndexes.size() >= 5 && !userSelectedIndexes.contains(in))
            {
                System.out.println("You can have 5 atoms selected at a time. Please deselect an atom by re-entering its index before selecting another.");
            }
            else if (userSelectedIndexes.contains(in))
            {
                System.out.println("Index " + in + " was deselected");

                //loop to find the index in the arraylist at which the atom to be deselected is placed
                for (int i = 0; i < userSelectedIndexes.size(); i++)
                {
                    if (in == userSelectedIndexes.get(i)) {
                        userSelectedAtoms.get(i).setVisible(false);
                        userSelectedAtoms.remove(i);
                        userSelectedIndexes.remove(i);
                        break;
                    }
                }
            }
            else {
                userSelectedAtoms.add(atoms.get(in));
                userSelectedIndexes.add(in);
                atoms.get(in).setVisible(true);
                System.out.println("Current list of indexes: " + userSelectedIndexes);
            }
        } catch(Exception ex)
        {
            System.out.println("Please enter the hexagon index of ONE atom you would like to select as an integer.");
            ex.printStackTrace();
        }
    }
    /**
     * Displays the atoms that have been randomly selected
     */
    @FXML
    public void endRound() {
        if (userSelectedAtoms.size() != 5)
        {
            System.out.println("Please select 5 atoms before ending the round");
        }
        else {
            for (int i = 0; i < selectedAtoms.size(); i++) {
                selectedAtoms.get(i).setVisible(true); //set all atoms in arraylist to be visible
                selectedCirclesOfInfluence.get(i).setVisible(true);
            }
            for (ArrayList<Line> line : lines) {
                addLine(line);
            }
            for (ArrayList<Line> line : lines) {
                for (Line value : line) {
                    value.setVisible(true);
                }
            }
            addLine(gaps);
            for (Line gap : gaps)
            {
                gap.setVisible(true);
            }

            System.out.println("Score is: " + calculateScore());
        }
    }

    @FXML
    public void drawLine(int start, int end)
    {
        double startX = markersArr[start - 1][0];
        double startY = markersArr[start - 1][1];
        double endX = markersArr[end - 1][0];
        double endY = markersArr[end - 1][1];

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BEIGE);
        line.setStrokeWidth(3);
        labelContainer.getChildren().add(line);
    }

    @FXML
    public Label checkLabel(int n) {
        List<Node> markers = labelContainer.getChildren();
        if (markers.get(n) instanceof Label) {
            return (Label)markers.get(n);
        }
        else {
            throw new IllegalStateException("Not a label");
        }
    }

    int colourIndex = 0;
    @FXML
    public void setMarker(int in, int out) {
        Label labelIn = checkLabel(in - 1);
        Label labelOut = checkLabel(out - 1);

        ArrayList<Color> colours = new ArrayList<>(List.of(
                Color.SLATEGREY, Color.TEAL, Color.FORESTGREEN, Color.DARKRED,
                Color.MEDIUMORCHID, Color.THISTLE, Color.STEELBLUE,
                Color.DARKSEAGREEN, Color.OLIVEDRAB,
                Color.NAVY, Color.MAROON, Color.PINK));

        try {
            Color backgroundColour = colours.get(colourIndex);
            labelIn.setBackground(Background.fill(backgroundColour));
            labelOut.setBackground(Background.fill(backgroundColour));
            colourIndex++;
        } catch (Exception ex) {
            System.out.println("Ran out of marker colours to assign.");
        }

    }

    /** A mapping between hexagon indexes and coordinates.
     *
     * @param index the index of hexagon
     * @return Double[] x and y coordinates
     */
    @FXML
    public Double[] hexCor(int index) {
        // get the circle in circle container
        Circle currCircle = atoms.get(index);
        // get the coordinates of the circle
        Double[] coordinates = new Double[2];
        coordinates[0] = currCircle.getCenterX();
        coordinates[1] = currCircle.getCenterY();
        return coordinates;
    }

    /** A mapping between labels and hexagon indexes. Both start from 0.
     * length = 54
     *
     */
    public int hexIndex(int label) {
        Integer[] labelHexIndex = new Integer[]{
                0, 0, 5, 5,
                11, 11, 18, 18,
                26, 26, 26,
                35, 35, 43, 43, 50, 50,
                56, 56, 56,
                57, 57, 58, 58, 59, 59,
                60, 60, 60,
                55, 55, 49, 49, 42, 42,
                34, 34, 34,
                25, 25, 17, 17, 10, 10,
                4, 4, 4,
                3, 3, 2, 2, 1, 1,
                0
        };
        return labelHexIndex[label];
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
        circlesOfInfluence = new ArrayList<Circle>();
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
                circlesOfInfluence.add(circle);
                row.getChildren().add(circle);
            }

            circleContainer.getChildren().add(row);
            if (i < 4) n++;
            else n--;
        }

    }

    /**
     * Get the gap line between label and 1st hexagon
     * @param startCoor
     * @param in
     * @return
     */
    private Line getGap1(SixtyDegreeTest.HexagonCoor startCoor, LabelMap in) {
        int hexIndex = startCoor.getIndex();
        int labelIndex = in.getLabelNum() - 1;
        //System.out.println("hexIndex: " + hexIndex );
        //System.out.println("labelIndex: " + labelIndex );

        Node hexagon = invisibleDots.getChildren().get(hexIndex);
        Node label = labelContainer.getChildren().get(labelIndex);

        //System.out.println("Gap: " + hexagon.getLayoutX() + " " + hexagon.getLayoutY() + " " + label.getLayoutX() + " " + label.getLayoutY());

        Line gap1 = new Line(hexagon.getLayoutX(), hexagon.getLayoutY(), label.getLayoutX(), label.getLayoutY());
        gap1.setStroke(Color.CYAN);
        gap1.setStrokeWidth(3); // make ray path thicker
        gap1.setVisible(false);
        return gap1;
    }

    public int calculateScore(){
        int errorCount = 0;
        int score = 0;
        for(int i = 0; i < userSelectedIndexes.size(); i++){
            if (!indexes.contains(userSelectedIndexes.get(i)))
            {
                errorCount++;
                userSelectedAtoms.get(i).setFill(Color.RED);
            }
            else if (indexes.contains(userSelectedIndexes.get(i)))
            {
                userSelectedAtoms.get(i).setFill(Color.GREEN);
            }
        }

        score = rayCount + (5 * errorCount);
        return score;
    }

}
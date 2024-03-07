package test.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class HelloController {
    @FXML
    private Button startButton;

    @FXML private Button rayButton;

    @FXML
    private VBox atomContainer;

    @FXML
    private Pane labelContainer;

    private ArrayList<Circle> atoms;

    private ArrayList<Circle> selectedAtoms = new ArrayList<>();
    private ArrayList<Circle> selectedCirclesOfInfluence = new ArrayList<>();

    @FXML
    private VBox circleContainer;
    @FXML
    private ArrayList<Circle> circlesOfInfluence;

    @FXML
    private Pane lineContainer;

    private double[][] markersArr = new double[54][2];

    public enum directions {southeast, southwest, west, northwest, northeast, east}
    ArrayList<Integer> indexes;


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
    }

    @FXML
    public LabelMap calcRay(int startLabel) {
        LabelMap in = new LabelMap(startLabel);
        SixtyDegreeTest mover = new SixtyDegreeTest();
        ArrayList<SixtyDegreeTest.HexagonCoor> atoms = new ArrayList<>();
        SixtyDegreeTest.HexagonCoor startCoor = mover.getCoorList().get(in.getIndex());

        for (Integer index : indexes) {
            atoms.add(mover.getCoorList().get(index)); //add atoms by index
        }

        return mover.moveRay(startCoor, atoms, in.getDirection());
    }

    public void sendRay() {
        int in = 8;
        LabelMap out = calcRay(in);
        setMarker(in, out.getLabelNum());
    }

    @FXML
    public int noHit(int start) {
        int out;
        if (start < 1)
        {
            throw new IllegalArgumentException("Input must be greater than 0");
        }

        if ((start <= 19 && start % 2 == 0) || (start >= 28 && start <= 46 && start % 2 == 1))
        {
            out = 47 - start;
            setMarker(start, out);
            return out;
        }

        else if (start <= 10 || start >= 19 && start <= 28 && start % 2 == 0)
        {
            out = 29 - start;
            setMarker(start, out);
            return out;
        }

        else if ((start <= 28  && start % 2 == 1)|| start >= 37 && start <= 54 && start % 2 == 0)
        {
            out = 65 - start;
            setMarker(start, out);
            return out;
        }

        else if ((start >= 27 && start <= 37 && start % 2 == 0) || (start >= 46 && start <= 54 && start % 2 == 1))
        {
            out = 83 - start;
            setMarker(start, out);
            return out;
        }

        else
        {
            throw new IllegalArgumentException("No valid output");
        }
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
    /**
     * Displays the atoms that have been randomly selected
     */
    @FXML
    public void displayAtoms() {
        for (int i = 0; i < selectedAtoms.size(); i++) {
            selectedAtoms.get(i).setVisible(true); //set all atoms in arraylist to be visible
            selectedCirclesOfInfluence.get(i).setVisible(true);
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
    public void setMarker(int in, int out)
    {
        Label labelIn = checkLabel(in - 1);
        Label labelOut = checkLabel(out - 1);

        ArrayList<Color> colours = new ArrayList<>(List.of(
                Color.SLATEGREY, Color.LIGHTCORAL, Color.TEAL, Color.MISTYROSE, Color.FORESTGREEN, Color.DARKRED,
                Color.SILVER, Color.WHEAT, Color.SPRINGGREEN, Color.POWDERBLUE, Color.GOLD, Color.MEDIUMORCHID,
                Color.KHAKI, Color.LIGHTCYAN, Color.CYAN, Color.THISTLE, Color.STEELBLUE, Color.LIGHTSALMON,
                Color.DARKVIOLET, Color.DARKSEAGREEN, Color.CORNFLOWERBLUE, Color.PEACHPUFF, Color.OLIVEDRAB,
                Color.NAVY, Color.MAROON, Color.LEMONCHIFFON, Color.PINK));

        Color backgroundColour = colours.get(colourIndex);
        labelIn.setBackground(Background.fill(backgroundColour));
        labelOut.setBackground(Background.fill(backgroundColour));
        colourIndex++;

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


    public static void userInput() {
        Scanner scanner = new Scanner(System.in);
        HelloController controller = new HelloController();
        System.out.println("Enter the number of the edge from which you would like to send a ray (1-54).");

        int input = scanner.nextInt();
        int output = controller.noHit(input);
        System.out.println("Ray entered at " + input);
        System.out.println("Ray exited at " + output);
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

}
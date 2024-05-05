package blackbox;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller layer, which contains the logic necessary to facilitate communications,
 acting as an interface between the view and model layers
 * it generates all atoms for the GUI in initialise() and controls their visibility to control what the user is to see
 * has data structures that store information from user input such as the atoms and ray edges selected to control GUI
 effects accordingly
 * draws rays using hexagon coordinates
 * calculates score based on user interaction
 */
public class Controller {

    @FXML private TextField rayField;

    @FXML private VBox atomContainer;

    @FXML private Pane labelContainer;

    @FXML Pane invisibleDots;

    @FXML private VBox circleContainer;
    @FXML private ArrayList<Circle> circlesOfInfluence;

    @FXML private TextField indexField;

    private ArrayList<ArrayList<Line>> lines = new ArrayList<>();

    private ArrayList<Line> gaps = new ArrayList<>();
    private ArrayList<Circle> atoms;
    private ArrayList<Circle> selectedAtoms = new ArrayList<>();
    private ArrayList<Circle> selectedCirclesOfInfluence = new ArrayList<>();
    private ArrayList<Circle> userSelectedAtoms = new ArrayList<>();
    private int rayCount = 0;
    private double[][] markersArr = new double[54][2];
    public enum directions {southeast, southwest, west, northwest, northeast, east}
    private ArrayList<Integer> indexes;
    private ArrayList<Integer> userSelectedIndexes = new ArrayList<>();
    protected static Double[][] realCoors = new Double[61][2];
    int colourIndex = 0;

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
        RayMovement mover = new RayMovement(); //calculates the ray path
        ArrayList<RayMovement.HexagonCoor> atoms = new ArrayList<>();
        //gets the coordinates of the start of the ray from the index of the start hexagon
        RayMovement.HexagonCoor startCoor = mover.getCoorList().get(in.getIndex());

        //the indexes arrayList is the index of each atom that is on the board
        for (Integer index : indexes) {
            atoms.add(mover.getCoorList().get(index)); //add atoms by index
        }

        LabelMap endLabel = mover.moveRay(startCoor, atoms, in.getDirection(), null);
        lines.add(mover.getLine());
        // add a line between label and startCoor
        Line gap1 = getGap1(startCoor, in);
        gaps.add(gap1);
        if (endLabel != null) {
            if (startLabel == endLabel.getLabelNum())
                System.out.println("Ray from edge " + startLabel + " was reflected");
            else
                System.out.println("Ray from edge " + startLabel + " exited at edge " + endLabel.getLabelNum());
            RayMovement.HexagonCoor endCoor = mover.getCoorList().get(endLabel.getIndex());
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
     * Takes input from the user, calculates a ray path, and outputs the result on the UI
     */
    public void sendRay() {
        try {
            int in = Integer.parseInt(rayField.getText()); //takes input from text field

            if (in >= 1 && in <= 54) {
                LabelMap out = calcRay(in);
                if (out == null) {
                    Label hit = checkLabel(in - 1);
                    hit.setText("H");
                } else if (out.getLabelNum() == in) {
                    Label reflect = checkLabel(in - 1);
                    reflect.setText("R");
                } else {
                    setMarker(in, out.getLabelNum());
                }
                rayCount++;
            }
            else {
                System.err.println("You must enter a label between 1 and 54.");
            }
        } catch (Exception ex) {
            System.err.println("You must enter a label between 1 and 54.");
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

    public void userSelectAtoms()
    {
        try {
            int in = Integer.parseInt(indexField.getText());

            if (in < 0 || in > 60)
                System.err.println("Input must be an integer between 0 and 60.");
            else {
                if (userSelectedIndexes.size() >= 5 && !userSelectedIndexes.contains(in)) {
                    System.err.println("You can have 5 atoms selected at a time. Please deselect an atom by re-entering its index before selecting another.");
                } else if (userSelectedIndexes.contains(in)) {
                    System.out.println("Index " + in + " was deselected");

                    //loop to find the index in the arraylist at which the atom to be deselected is placed
                    for (int i = 0; i < userSelectedIndexes.size(); i++) {
                        if (in == userSelectedIndexes.get(i)) {
                            userSelectedAtoms.get(i).setVisible(false);
                            userSelectedAtoms.remove(i);
                            userSelectedIndexes.remove(i);
                            break;
                        }
                    }
                } else {
                    userSelectedAtoms.add(atoms.get(in));
                    userSelectedIndexes.add(in);
                    atoms.get(in).setVisible(true);
                    System.out.println("Current list of indexes: " + userSelectedIndexes);
                }
            }
        } catch(Exception ex)
        {
            System.err.println("Please enter the hexagon index of ONE atom you would like to select as an integer.");
        }
    }
    /**
     * Displays the atoms that have been randomly selected
     */
    @FXML
    public void endRound() {
        if (userSelectedAtoms.size() != 5)
        {
            System.err.println("Please select 5 atoms before ending the round");
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
    public Label checkLabel(int n) {
        List<Node> markers = labelContainer.getChildren();
        if (markers.get(n) instanceof Label) {
            return (Label)markers.get(n);
        }
        else {
            throw new IllegalStateException("Not a label");
        }
    }

    @FXML
    public void setMarker(int in, int out) {
        Label labelIn = checkLabel(in - 1);
        Label labelOut = checkLabel(out - 1);

        ArrayList<Color> colours = new ArrayList<>(List.of(
                Color.SLATEGREY.darker(), Color.TEAL, Color.GREEN.darker(), Color.DARKRED,
                Color.MEDIUMORCHID, Color.STEELBLUE, Color.OLIVEDRAB,
                Color.NAVY, Color.SADDLEBROWN, Color.CORAL, Color.DEEPPINK, Color.DARKGOLDENROD, Color.INDIGO));

        try {
            Color backgroundColour = colours.get(colourIndex);
            labelIn.setBackground(Background.fill(backgroundColour));
            labelOut.setBackground(Background.fill(backgroundColour));
            colourIndex++;
        } catch (Exception ex) {
            System.out.println("Ran out of marker colours to assign.");
        }

    }

    /**
     * Generates hidden atoms in each hexagon and adds them to an arraylist
     */
    public void generateAtoms() {
        atoms = new ArrayList<>();
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
        circlesOfInfluence = new ArrayList<>();
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
     * @param startCoor HexagonCoor from which we are drawing a line
     * @param in Label of the start edge
     * @return a Line object
     */
    private Line getGap1(RayMovement.HexagonCoor startCoor, LabelMap in) {
        int hexIndex = startCoor.getIndex();
        int labelIndex = in.getLabelNum() - 1;

        Node hexagon = invisibleDots.getChildren().get(hexIndex);
        Node label = labelContainer.getChildren().get(labelIndex);

        Line gap1 = new Line(hexagon.getLayoutX(), hexagon.getLayoutY(), label.getLayoutX(), label.getLayoutY());
        gap1.setStroke(Color.CYAN);
        gap1.setStrokeWidth(3); // make ray path thicker
        gap1.setVisible(false);
        return gap1;
    }

    public int calculateScore(){
        int errorCount = 0;
        for(int i = 0; i < userSelectedIndexes.size(); i++){
            if (!indexes.contains(userSelectedIndexes.get(i)))
            {
                errorCount++;
                userSelectedAtoms.get(i).setFill(Color.RED);
            }
            else
            {
                userSelectedAtoms.get(i).setFill(Color.GREEN);
            }
        }

        return rayCount + (5 * errorCount);
    }

}
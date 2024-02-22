package test.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;


public class HelloController {
    @FXML
    public Button startButton;
    @FXML
    public Circle atom1;

    @FXML
    public void displayAtoms() {
        atom1.setVisible(true);
    }
}
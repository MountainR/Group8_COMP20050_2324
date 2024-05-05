/**
 * Launcher class is a JavaFX application which extends Application class provided by JavaFX
 * It will prompt a GUI for the black box game.
 */

package blackbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        System.out.println("Welcome to Black Box!");
        System.out.println("Instructions to play:");
        System.out.println("In the text field at the top left of the screen, enter the number of the edge at which you would like to send a ray");
        System.out.println("- If the ray hits an atom, the number will change to the letter H");
        System.out.println("- If the ray is reflected, the number will change to the letter R");
        System.out.println("- Otherwise, the labels of the entrance and exit edge will be highlighted");
        System.out.println("\nIn the text field at the bottom left of the screen, enter the number of the hexagon you would like to select as containing an atom");
        System.out.println("You may select 5 atoms at a time. Please enter the indexes into the text field one by one.");
        System.out.println("You may deselect an atom by entering its index again.");
        System.out.println("Once you have chosen 5 atoms, press the 'End Round' button to end the game and receive your score.\n");
        System.out.println("Each ray that is sent adds 1 point to your score, and each incorrectly placed atom adds 5 points to your score.\n");
    }

    public static void main(String[] args) {
        launch();
    }
}

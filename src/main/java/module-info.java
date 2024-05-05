module test.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens blackbox to javafx.fxml;
    exports blackbox;
}
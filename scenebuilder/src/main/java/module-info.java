module test.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens test.demo to javafx.fxml;
    exports test.demo;
}
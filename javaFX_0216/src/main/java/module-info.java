module com.example.javafx_0216 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_0216 to javafx.fxml;
    exports com.example.javafx_0216;
}
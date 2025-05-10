module com.example.atm7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.atm7 to javafx.fxml;
    exports com.example.atm7;
}
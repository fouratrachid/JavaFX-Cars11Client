module com.example.javafxcars {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxcars to javafx.fxml;
    exports com.example.javafxcars;

}
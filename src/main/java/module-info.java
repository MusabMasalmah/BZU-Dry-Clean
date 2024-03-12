module com.example.database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.database to javafx.fxml;
    exports com.example.database;
}
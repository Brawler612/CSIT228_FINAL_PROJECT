module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
}
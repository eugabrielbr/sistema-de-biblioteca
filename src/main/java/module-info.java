module com.example.sistemadebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.sistemadebiblioteca to javafx.fxml;
    exports com.example.sistemadebiblioteca;
    exports com.example.sistemadebiblioteca.dao;
    exports com.example.sistemadebiblioteca.controller;
    opens com.example.sistemadebiblioteca.controller to javafx.fxml;
    opens com.example.sistemadebiblioteca.dao to javafx.fxml;


}
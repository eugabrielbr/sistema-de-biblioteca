module com.example.sistemadebiblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistemadebiblioteca to javafx.fxml;
    exports com.example.sistemadebiblioteca;
}
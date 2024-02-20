package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GerenUsuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button HistUsu;

    @FXML
    private Button bloq;

    @FXML
    private Button regUsu;

    @FXML
    private Button voltar;

    @FXML
    void HistUsuAction(ActionEvent event) {
        HelloApplication.changeScene("historicouser");
    }

    @FXML
    void bloqUsuAction(ActionEvent event) {
        HelloApplication.changeScene("bloquear");
    }

    @FXML
    void regUsuAction(ActionEvent event) {
        HelloApplication.changeScene("registro");
    }

    @FXML
    void voltAction(ActionEvent event) {
        HelloApplication.changeScene("administrador");
    }

    @FXML
    void initialize() {
        assert HistUsu != null : "fx:id=\"HistUsu\" was not injected: check your FXML file 'gerenUsu-view.fxml'.";
        assert bloq != null : "fx:id=\"bloq\" was not injected: check your FXML file 'gerenUsu-view.fxml'.";
        assert regUsu != null : "fx:id=\"regUsu\" was not injected: check your FXML file 'gerenUsu-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'gerenUsu-view.fxml'.";

    }

}

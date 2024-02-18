package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buscar;

    @FXML
    private Button renovar;

    @FXML
    private Button reservar;

    @FXML
    private Button sair;

    @FXML
    void buscarAction(ActionEvent event) {
        HelloApplication.changeScene("pesquisa");
    }

    @FXML
    void renovarAction(ActionEvent event) {



        HelloApplication.changeScene("renovar");
    }

    @FXML
    void reservAction(ActionEvent event) {
        HelloApplication.changeScene("reserva");
    }
    @FXML
    void sairAction(ActionEvent event) {

        HelloController.isLei = false;
        HelloController.isAdm = false;
        HelloController.isBib = false;
        HelloApplication.changeScene("hello");
    }

    @FXML
    void initialize() {
        assert buscar != null : "fx:id=\"buscar\" was not injected: check your FXML file 'helloUser-view.fxml'.";
        assert renovar != null : "fx:id=\"renovar\" was not injected: check your FXML file 'helloUser-view.fxml'.";
        assert reservar != null : "fx:id=\"reservar\" was not injected: check your FXML file 'helloUser-view.fxml'.";
        assert sair != null : "fx:id=\"sair\" was not injected: check your FXML file 'helloUser-view.fxml'.";
    }

}

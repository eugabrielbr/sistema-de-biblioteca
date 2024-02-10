package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoTeste;


    @FXML
    void botaoVoltarAction(ActionEvent event) {

        HelloController.isAdm = false;
        HelloController.isBib = false;
        HelloController.isLei = false;

        HelloApplication.changeScene("hello");
    }



    @FXML
    void initialize() {

        assert botaoTeste != null : "fx:id=\"botaoTeste\" was not injected: check your FXML file 'login-view.fxml'.";

    }

}

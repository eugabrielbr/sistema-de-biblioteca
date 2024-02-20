package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloAdmController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buscar;

    @FXML
    private Button gerenAcervo;

    @FXML
    private Button gerenOperadores;

    @FXML
    private Button gerenUsuario;

    @FXML
    private Button relatorio;

    @FXML
    private Button sair;

    @FXML
    void buscarAction(ActionEvent event) {
        HelloApplication.changeScene("pesquisa");
    }

    @FXML
    void gerenAcervoAction(ActionEvent event) {
        HelloApplication.changeScene("gerenacervo");
    }

    @FXML
    void gerenOperadores(ActionEvent event) {
        HelloApplication.changeScene("registroadm");
    }

    @FXML
    void gerenUsuarioAction(ActionEvent event) {
        HelloApplication.changeScene("gerenusu");
    }

    @FXML
    void relatorioAction(ActionEvent event) {
        HelloApplication.changeScene("relatorio");
    }

    @FXML
    void sairAction(ActionEvent event) {

        HelloController.isAdm = false;
        HelloController.isLei = false;
        HelloController.isBib = false;
        HelloApplication.changeScene("hello");
    }

    @FXML
    void initialize() {
        assert buscar != null : "fx:id=\"buscar\" was not injected: check your FXML file 'helloAdm-view.fxml'.";
        assert gerenAcervo != null : "fx:id=\"gerenAcervo\" was not injected: check your FXML file 'helloAdm-view.fxml'.";
        assert gerenOperadores != null : "fx:id=\"gerenOperadores\" was not injected: check your FXML file 'helloAdm-view.fxml'.";
        assert gerenUsuario != null : "fx:id=\"gerenUsuario\" was not injected: check your FXML file 'helloAdm-view.fxml'.";
        assert relatorio != null : "fx:id=\"relatorio\" was not injected: check your FXML file 'helloAdm-view.fxml'.";
        assert sair != null : "fx:id=\"sair\" was not injected: check your FXML file 'helloAdm-view.fxml'.";

    }

}

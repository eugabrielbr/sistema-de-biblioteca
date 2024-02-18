package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloBlibController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acervo;

    @FXML
    private Button emprestimoDevolucao;

    @FXML
    private Button pesquisa;

    @FXML
    private Button sair;

    @FXML
    void acervoAction(ActionEvent event) {
        HelloApplication.changeScene("registrarlivro");
    }

    @FXML
    void emprestimoDevolucaoAction(ActionEvent event) {
        HelloApplication.changeScene("empresdevol");
    }

    @FXML
    void pesquisaAction(ActionEvent event) {
        HelloApplication.changeScene("pesquisa");
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
        assert acervo != null : "fx:id=\"acervo\" was not injected: check your FXML file 'helloBlib-view.fxml'.";
        assert emprestimoDevolucao != null : "fx:id=\"emprestimoDevolucao\" was not injected: check your FXML file 'helloBlib-view.fxml'.";
        assert pesquisa != null : "fx:id=\"pesquisa\" was not injected: check your FXML file 'helloBlib-view.fxml'.";
        assert sair != null : "fx:id=\"sair\" was not injected: check your FXML file 'helloBlib-view.fxml'.";

    }

}

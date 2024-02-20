package com.example.sistemadebiblioteca.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GerenAcervoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button attLivro;

    @FXML
    private Button deletar;

    @FXML
    private Button regLivro;

    @FXML
    private Button voltar;

    @FXML
    void attLivroAction(ActionEvent event) {
        HelloApplication.changeScene("atualizarlivro");
    }

    @FXML
    void deletarAction(ActionEvent event) {
        HelloApplication.changeScene("deletarlivro");
    }

    @FXML
    void regLivroAction(ActionEvent event) {
        HelloApplication.changeScene("registrarlivro");
    }

    @FXML
    void voltAction(ActionEvent event) {
        HelloApplication.changeScene("administrador");
    }

    @FXML
    void initialize() {
        assert attLivro != null : "fx:id=\"attLivro\" was not injected: check your FXML file 'gerenAcervo-view.fxml'.";
        assert deletar != null : "fx:id=\"deletar\" was not injected: check your FXML file 'gerenAcervo-view.fxml'.";
        assert regLivro != null : "fx:id=\"regLivro\" was not injected: check your FXML file 'gerenAcervo-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'gerenAcervo-view.fxml'.";

    }

}

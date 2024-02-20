package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeletarLivroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deletar;

    @FXML
    private Label textAlert;

    @FXML
    private TextField textID;

    @FXML
    private Button voltar;

    @FXML
    void deletarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        try{
            DAO.getLivroDAO().delete(Integer.parseInt(textID.getText()));
            textAlert.setStyle("-fx-text-fill: #69B00B;");
            textAlert.setText("Livro deletado com sucesso!");

        }catch (DAOExceptions e){
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Falha ao deletar! Verifique se as informações estão corretas.");
        }
        catch (Exception z){
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Falha ao deletar!");
        }
    }

    @FXML
    void voltarAction(ActionEvent event) {
        textID.clear();
        textAlert.setText("");
        HelloApplication.changeScene("gerenacervo");
    }

    @FXML
    void initialize() {
        assert deletar != null : "fx:id=\"deletar\" was not injected: check your FXML file 'deletarLivro-view.fxml'.";
        assert textAlert != null : "fx:id=\"textAlert\" was not injected: check your FXML file 'deletarLivro-view.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'deletarLivro-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'deletarLivro-view.fxml'.";

    }

}

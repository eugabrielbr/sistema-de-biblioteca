package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.AdmUseCases;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BloquearController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bloquear;

    @FXML
    private Button desbloquear;

    @FXML
    private Label textAlert;

    @FXML
    private TextField textID;

    @FXML
    private Button voltar;

    private AdmUseCases admUseCases;

    @FXML
    void bloquearAction(ActionEvent event) throws IOException, ClassNotFoundException {

        try{

            Usuario usuario = DAO.getUsuarioDAO().findById(Integer.parseInt(textID.getText()));
            this.admUseCases.bloquearDesbloquearUsuario(Integer.parseInt(textID.getText()),1,DAO.getUsuarioDAO());
            textAlert.setStyle("-fx-text-fill: #69B00B;");
            textAlert.setText("Usuário bloqueado com sucesso!");

        } catch (DAOExceptions  e) {
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Erro ao bloquear! Verifique se as informações estão corretas.");
        }
        catch (Exception z){
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Erro ao bloquear!");
        }


    }

    @FXML
    void desbloquearAction(ActionEvent event) {

        try{

            Usuario usuario = DAO.getUsuarioDAO().findById(Integer.parseInt(textID.getText()));
            this.admUseCases.bloquearDesbloquearUsuario(Integer.parseInt(textID.getText()),2,DAO.getUsuarioDAO());
            textAlert.setStyle("-fx-text-fill: #69B00B;");
            textAlert.setText("Usuário desbloqueado com sucesso!");

        } catch (DAOExceptions  e) {
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Erro ao desbloquear! Verifique se as informações estão corretas.");
        }
        catch (Exception z){
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Erro ao desbloquear!");
        }
    }

    @FXML
    void voltarAction(ActionEvent event) {
        textAlert.setText("");
        textID.clear();
        HelloApplication.changeScene("gerenusu");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert bloquear != null : "fx:id=\"bloquear\" was not injected: check your FXML file 'bloquearUser-view.fxml'.";
        assert desbloquear != null : "fx:id=\"desbloquear\" was not injected: check your FXML file 'bloquearUser-view.fxml'.";
        assert textAlert != null : "fx:id=\"textAlert\" was not injected: check your FXML file 'bloquearUser-view.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'bloquearUser-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'bloquearUser-view.fxml'.";
        this.admUseCases = new AdmUseCases();
    }

}

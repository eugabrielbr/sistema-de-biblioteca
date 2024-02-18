package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.LocalDateNow;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.exceptions.usecases.UsuarioUseCasesExceptions;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Usuario;
import com.example.sistemadebiblioteca.model.UsuarioUseCases;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ReservaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoReservar;

    @FXML
    private TextField textID;

    @FXML
    private Button voltar;
    @FXML
    private Label textAlert;

    private UsuarioUseCases usuarioUseCases;



    @FXML
    void botaoReservarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {



        try{

            textAlert.setText("");
            Livro livro = DAO.getLivroDAO().findById(Integer.parseInt(textID.getText()));
            usuarioUseCases.reservarLivros(livro.getID(), LoginController.usuario.getID(),LocalDateNow.localDateNow,DAO.getLivroDAO(),DAO.getUsuarioDAO());
            textAlert.setStyle("-fx-text-fill: #69B00B;");
            textAlert.setText("Reserva efetuada com sucesso!");



        } catch (UsuarioUseCasesExceptions e) {

            textAlert.setText("");
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText(e.getMessage());

        }catch (DAOExceptions x){

            textAlert.setText("");
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText(x.getMessage());
        }
        catch (Exception z){

            textAlert.setText("");
            textAlert.setStyle("-fx-text-fill: #AE0001;");
            textAlert.setText("Erro ao reservar!");
        }


    }

    @FXML
    void voltarAction(ActionEvent event) {
        textID.clear();
        textAlert.setText("");
        HelloApplication.changeScene("usuario");
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert botaoReservar != null : "fx:id=\"botaoReservar\" was not injected: check your FXML file 'reserva-view.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'reserva-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'reserva-view.fxml'.";
        assert textAlert != null : "fx:id=\"textAlert\" was not injected: check your FXML file 'reserva-view.fxml'.";
        this.usuarioUseCases = new UsuarioUseCases();
    }

}

package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Operadores;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoAdm;

    @FXML
    private Button botaoBib;

    @FXML
    private Button botaoLeitor;
    public static boolean isAdm;
    public static boolean isBib;
    public static boolean isLei;


    @FXML
    void botaoAdmAction(ActionEvent event) throws IOException, ClassNotFoundException {

        isAdm = true;
        HelloApplication.changeScene("administrador");

    }


    @FXML
    void botaoBibAction(ActionEvent event) throws IOException, ClassNotFoundException {

        isBib = true;
        HelloApplication.changeScene("login");

    }

    @FXML
    void botaoLeitorAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {


        isLei = true;
        HelloApplication.changeScene("login");

    }

    @FXML
    void initialize() {
        assert botaoAdm != null : "fx:id=\"botaoAdm\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert botaoBib != null : "fx:id=\"botaoBib\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert botaoLeitor != null : "fx:id=\"botaoLeitor\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}

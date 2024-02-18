package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class InfoBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label textAutor;

    @FXML
    private Label textCateg;

    @FXML
    private Label textDisp;

    @FXML
    private Label textEditora;

    @FXML
    private Label textID;

    @FXML
    private Label textISBN;

    @FXML
    private Label textLoc;

    @FXML
    private Label textTitulo;
    @FXML
    private Button voltar;

    @FXML
    private Hyperlink hyperLink;



    @FXML
    void hyperLinkAction( ActionEvent event) {

        this.textTitulo.setText("Título: " + PesquisaLivroController.livroEscolhido.getTitulo());
        this.textAutor.setText("Autor: " + PesquisaLivroController.livroEscolhido.getAutor());
        this.textEditora.setText("Editora: " + PesquisaLivroController.livroEscolhido.getEditora());
        this.textCateg.setText("Categoria: " + PesquisaLivroController.livroEscolhido.getCategoria());
        this.textISBN.setText("ISBN: " +  PesquisaLivroController.livroEscolhido.getISBN());
        this.textLoc.setText("Localização: " + PesquisaLivroController.livroEscolhido.getLocalizacao());
        this.textID.setText("ID: " + PesquisaLivroController.livroEscolhido.getID());

        if (PesquisaLivroController.livroEscolhido.getDisponibilidade()){
            textDisp.setStyle("-fx-text-fill: #69B00B;");
            this.textDisp.setText("Disponível");
        }
        else{
            textDisp.setStyle("-fx-text-fill: #AE0001;");
            this.textDisp.setText("Indisponível");
        }


    }

    @FXML
    void voltarAction(ActionEvent event) throws IOException, ClassNotFoundException {


        this.textTitulo.setText("");
        this.textAutor.setText("");
        this.textEditora.setText("");
        this.textCateg.setText("");
        this.textISBN.setText("");
        this.textLoc.setText("");
        this.textID.setText("");
        this.textDisp.setText("");

        HelloApplication.changeScene("pesquisa");

    }

    @FXML
    void initialize() {

        assert textAutor != null : "fx:id=\"textAutor\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textCateg != null : "fx:id=\"textCateg\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textDisp != null : "fx:id=\"textDisp\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textEditora != null : "fx:id=\"textEditora\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textISBN != null : "fx:id=\"textISBN\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textLoc != null : "fx:id=\"textLoc\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert textTitulo != null : "fx:id=\"textTitulo\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert hyperLink != null : "fx:id=\"hyperLink\" was not injected: check your FXML file 'infoBook-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'infoBook-view.fxml'.";

    }

}

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

public class AtualizarLivroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alertText;

    @FXML
    private Button atualizar;

    @FXML
    private TextField autor;

    @FXML
    private TextField categoria;

    @FXML
    private TextField editora;

    @FXML
    private TextField isbn;

    @FXML
    private TextField loc;

    @FXML
    private TextField textID;

    @FXML
    private TextField titulo;

    @FXML
    private Button voltar;

    @FXML
    void atualizarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        try{




            if (!titulo.getText().isEmpty() || !autor.getText().isEmpty() || !editora.getText().isEmpty() || !categoria.getText().isEmpty() || !isbn.getText().isEmpty() || !loc.getText().isEmpty()){

                if (!textID.getText().isEmpty()){

                    Livro livro = DAO.getLivroDAO().findById(Integer.parseInt(textID.getText()));

                    if (!titulo.getText().isEmpty()){
                        livro.setTitulo(titulo.getText());
                    }

                    if(!autor.getText().isEmpty()){
                        livro.setAutor(autor.getText());
                    }

                    if(!editora.getText().isEmpty()){
                        livro.setEditora(editora.getText());
                    }

                    if(!categoria.getText().isEmpty()){
                        livro.setCategoria(categoria.getText());
                    }

                    if(!isbn.getText().isEmpty()){
                        livro.setISBN(Integer.parseInt(isbn.getText()));
                    }

                    if (!loc.getText().isEmpty()){
                        livro.setLocalizacao(loc.getText());
                    }

                    DAO.getLivroDAO().update(livro,Integer.parseInt(textID.getText()));
                    alertText.setStyle("-fx-text-fill: #69B00B;");
                    alertText.setText("Livro atualizado!");
                }
                else{
                    alertText.setStyle("-fx-text-fill: #AE0001;");
                    alertText.setText("Informe o ID do livro!");
                }
            }
            else{
                alertText.setStyle("-fx-text-fill: #AE0001;");
                alertText.setText("Informe ao menos um campo que deseja atualizar.");
            }
        }
        catch (DAOExceptions e){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Erro ao atualizar! Verifique se o ID informado está correto.");
        }
        catch (Exception x){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Erro ao atualizar!");
        }

    }

    @FXML
    void voltarAction(ActionEvent event) {
        autor.clear();
        titulo.clear();
        editora.clear();
        categoria.clear();
        isbn.clear();
        loc.clear();
        textID.clear();
        alertText.setText("");
        HelloApplication.changeScene("gerenacervo");
    }

    @FXML
    void initialize() {
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert atualizar != null : "fx:id=\"atualizar\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert loc != null : "fx:id=\"loc\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'atualizarLivro-view.fxml'.";

    }

}

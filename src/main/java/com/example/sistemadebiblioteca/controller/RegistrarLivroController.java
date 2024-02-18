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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrarLivroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alertText;

    @FXML
    private TextField autor;

    @FXML
    private Button botaoRegistrar;

    @FXML
    private TextField categoria;

    @FXML
    private TextField editora;

    @FXML
    private TextField isbn;

    @FXML
    private TextField loc;

    @FXML
    private TextField titulo;

    @FXML
    private Button voltar;

    @FXML
    private TextField qtd;

    @FXML
    void botaoRegistrarAction(ActionEvent event) throws IOException, ClassNotFoundException {

        try {

            if (!titulo.getText().isEmpty() && !autor.getText().isEmpty() && !categoria.getText().isEmpty() && !loc.getText().isEmpty() && !isbn.getText().isEmpty() && !editora.getText().isEmpty()) {

                Integer i;

                for (i = 0; i < Integer.parseInt(qtd.getText()); i++) {
                    System.out.println(i);
                    Livro livro = new Livro(titulo.getText(),autor.getText(),editora.getText(),Integer.parseInt(isbn.getText()),categoria.getText(),loc.getText());
                    DAO.getLivroDAO().create(livro);
                }
                alertText.setStyle("-fx-text-fill: #69B00B;");
                alertText.setText("Livros registrados com sucesso! Total: "+ i +" unidades");
                clear();

            }else{
                alertText.setStyle("-fx-text-fill: #AE0001;");
                alertText.setText("Preencha todos os campos!");
            }


        }catch (Exception e){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText(e.getMessage());
        }

    }


    public void clear(){
        titulo.clear();
        autor.clear();
        categoria.clear();
        loc.clear();
        isbn.clear();
        editora.clear();
        qtd.clear();

    }

    @FXML
    void voltarAction(ActionEvent event) {

        if(HelloController.isAdm){
            //muda para cena hello adm
        }
        else if(HelloController.isBib){
            HelloApplication.changeScene("bibliotecario");
        }
    }

    @FXML
    void initialize() {
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'Untitled'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'Untitled'.";
        assert botaoRegistrar != null : "fx:id=\"botaoRegistrar\" was not injected: check your FXML file 'Untitled'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'Untitled'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'Untitled'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'Untitled'.";
        assert loc != null : "fx:id=\"loc\" was not injected: check your FXML file 'Untitled'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'Untitled'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'Untitled'.";
        assert qtd != null : "fx:id=\"qtd\" was not injected: check your FXML file 'Untitled'.";

    }

}

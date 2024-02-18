package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.dao.livro.LivroDAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PesquisaLivroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoBuscar;
    @FXML
    private Button botaoVoltar;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button botaoAcessar;


    @FXML
    private TextField pesquisaText;
    @FXML
    private Label textAlert;

    @FXML
    private ListView<String> listLivros;
    private String[] options = {"Título","Autor","ISBN","Categoria"};
    private String[] optionBook;
    private List<Livro> lista = new ArrayList<>();
    public static Livro livroEscolhido;


    public void convertListForArray(List<Livro> lista) throws IOException, ClassNotFoundException, DAOExceptions {

        this.optionBook = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++){

            Livro value = lista.get(i);


            this.optionBook[i] = value.getTitulo() + " - ID: " + value.getID();
        }

        listLivros.getItems().addAll(optionBook);

    }
    @FXML
    void botaoBuscarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        clear();
        listLivros.refresh();

        try {

            if (choiceBox.getValue().equals("Título")) {
                this.lista = DAO.getLivroDAO().findByTitulo(pesquisaText.getText());
                convertListForArray(lista);
            }
            else if (choiceBox.getValue().equals("Autor")){
                this.lista = DAO.getLivroDAO().findByAutor(pesquisaText.getText());
                convertListForArray(lista);
            }
            else if (choiceBox.getValue().equals("ISBN")){
                this.lista = DAO.getLivroDAO().findByISBN(Integer.parseInt(pesquisaText.getText()));
                convertListForArray(lista);
            }
            else if (choiceBox.getValue().equals("Categoria")){
                this.lista = DAO.getLivroDAO().findByCategoria(pesquisaText.getText());
                convertListForArray(lista);
            }
        }
        catch (Exception e ){

            textAlert.setText("Livro não encontrado!");
        }

    }
    @FXML
    void botaoAcessarAction(ActionEvent event) {


        Integer index = this.listLivros.getSelectionModel().getSelectedIndex();

        if (index != null){
            textAlert.setText("");
        }

        try {
            livroEscolhido = lista.get(index);
            HelloApplication.changeScene("infoLivro");
        }
        catch (Exception e){
            textAlert.setText("Selecione um livro!");
        }


    }

    public void clear(){

        listLivros.getItems().clear();
        textAlert.setText("");

    }

    @FXML
    void botaoVoltarAction(ActionEvent event) {

        clear();
        pesquisaText.clear();
        choiceBox.setValue("");

        if(HelloController.isLei){
            HelloApplication.changeScene("usuario");
        }
        else if(HelloController.isAdm){
            HelloApplication.changeScene("administrador");
        }
        else if(HelloController.isBib){
            HelloApplication.changeScene("bibliotecario");
        }

    }



    @FXML
    void initialize() {

        assert botaoAcessar != null : "fx:id=\"botaoAcessar\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert botaoBuscar != null : "fx:id=\"botaoBuscar\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert pesquisaText != null : "fx:id=\"pesquisaText\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert listLivros != null : "fx:id=\"listLivros\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        assert textAlert != null : "fx:id=\"textAlert\" was not injected: check your FXML file 'pesquisaLivro-view.fxml'.";
        choiceBox.getItems().addAll(options);




    }

}

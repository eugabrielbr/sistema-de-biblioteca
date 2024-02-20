package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.LocalDateNow;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.exceptions.usecases.BlibUseCaseExceptions;
import com.example.sistemadebiblioteca.model.BlibUseCases;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmprestimoDevolucaoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField IDlivro;

    @FXML
    private TextField IDusuario;

    @FXML
    private Label alertText;

    @FXML
    private Button botaoDevolver;
    @FXML
    private Button Voltar;


    @FXML
    private Button botaoEmprestar;
    private BlibUseCases blibUseCases;

    @FXML
    void VoltarAction(ActionEvent event) {
        clear();
        HelloApplication.changeScene("bibliotecario");
    }

    @FXML
    void botaoDevolverAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions, BlibUseCaseExceptions {
        alertText.setText("");
        try {

            blibUseCases.registroDevolucao(Integer.parseInt(IDlivro.getText()),Integer.parseInt(IDusuario.getText()),DAO.getEmprestimoDAO(),DAO.getLivroDAO(),DAO.getUsuarioDAO(),LocalDateNow.localDateNow);
            alertText.setStyle("-fx-text-fill: #69B00B;");
            alertText.setText("Devolução bem-sucedida!");
        }
        catch (DAOExceptions e){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Não foi possível registrar a devolução. Verifique se as informações estão corretas.");
        }
        catch (BlibUseCaseExceptions b){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText(b.getMessage());
        }
        catch (Exception z){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Não foi possível efetuar a devolução"+ z.getMessage());
        }



    }
    public void clear(){
        IDlivro.clear();
        IDusuario.clear();
        alertText.setText("");
    }
    @FXML
    void botaoEmprestarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions, BlibUseCaseExceptions {

        alertText.setText("");


        try {

            Usuario user = DAO.getUsuarioDAO().findById(Integer.parseInt(IDusuario.getText()));
            Livro livro = DAO.getLivroDAO().findById(Integer.parseInt(IDlivro.getText()));
            Emprestimo emprestimo = new Emprestimo(user, LocalDateNow.localDateNow, livro);

            blibUseCases.registroEmprestimo(emprestimo,DAO.getLivroDAO(),DAO.getUsuarioDAO(),DAO.getEmprestimoDAO(),LocalDateNow.localDateNow);
            alertText.setStyle("-fx-text-fill: #69B00B;");
            alertText.setText("Empréstimo registrado! A data de devolução é " + DAO.getEmprestimoDAO().findByIDlivroIDusuario(livro.getID(),user.getID()).getDataDevolucao());

        }
        catch (DAOExceptions e){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Empréstimo mal-sucedido! Verifique se as informações estão corretas");

        }
        catch (BlibUseCaseExceptions b){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText(b.getMessage());
        }
        catch (Exception z){
            alertText.setText("Não foi possível efetuar o empréstimo"+ z.getMessage());
        }



    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert IDlivro != null : "fx:id=\"IDlivro\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        assert IDusuario != null : "fx:id=\"IDusuario\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        assert botaoDevolver != null : "fx:id=\"botaoDevolver\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        assert botaoEmprestar != null : "fx:id=\"botaoEmprestar\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        assert Voltar != null : "fx:id=\"Voltar\" was not injected: check your FXML file 'emprestimoDevolucao-view.fxml'.";
        this.blibUseCases = new BlibUseCases();
    }

}

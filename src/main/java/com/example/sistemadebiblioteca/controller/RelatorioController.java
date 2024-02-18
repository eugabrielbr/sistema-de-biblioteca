package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import javafx.scene.control.Button;

import com.example.sistemadebiblioteca.LocalDateNow;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.model.AdmUseCases;
import com.example.sistemadebiblioteca.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class RelatorioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink hyperLink;

    @FXML
    private Label livrosAtrados;

    @FXML
    private Label livrosEmprestados;

    @FXML
    private Label livrosPopulares;

    @FXML
    private Label livrosReservados;
    @FXML
    private Button voltar;
    private AdmUseCases admUseCases;


    @FXML
    void hyperLinkAction(ActionEvent event) throws IOException, ClassNotFoundException {

        Integer lEmprestados = admUseCases.numeroLivrosEmprestados(DAO.getEmprestimoDAO());
        Integer lAtrasados = admUseCases.qtdLivrosAtrasados(DAO.getEmprestimoDAO(), LocalDateNow.localDateNow);
        Integer lreservados = admUseCases.qtdLivrosReservados(DAO.getLivroDAO());
        List<Livro> lPopulares = admUseCases.livrosMaisPopulares(DAO.getLivroDAO());

        this.livrosEmprestados.setText("Total de livros emprestados: " + String.valueOf(lEmprestados));
        this.livrosAtrados.setText("Total de livros atrasados: " + String.valueOf(lAtrasados));
        this.livrosReservados.setText("Total de livros reservados: " + String.valueOf(lreservados));

        if (lPopulares != null){

            if (lPopulares.size() == 5){
                this.livrosPopulares.setText("Top 5 livros mais populares:\n" +
                        "     - " + lPopulares.get(0).getTitulo() + " (" + lPopulares.get(0).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - "  + lPopulares.get(1).getTitulo() + " (" + lPopulares.get(1).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(2).getTitulo() + " (" + lPopulares.get(2).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(3).getTitulo() + " (" + lPopulares.get(3).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(4).getTitulo() + " (" + lPopulares.get(4).getQtdEmprestimo() + " empréstimos)\n");

            }else if (lPopulares.size() == 4){
                this.livrosPopulares.setText("Top 5 livros mais populares:\n" +
                        "     - " + lPopulares.get(0).getTitulo() + " (" + lPopulares.get(0).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - "  + lPopulares.get(1).getTitulo() + " (" + lPopulares.get(1).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(2).getTitulo() + " (" + lPopulares.get(2).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(3).getTitulo() + " (" + lPopulares.get(3).getQtdEmprestimo() + " empréstimos)\n");
            }else if (lPopulares.size() == 3){
                this.livrosPopulares.setText("Top 5 livros mais populares:\n" +
                        "     - " + lPopulares.get(0).getTitulo() + " (" + lPopulares.get(0).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - "  + lPopulares.get(1).getTitulo() + " (" + lPopulares.get(1).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - " + lPopulares.get(2).getTitulo() + " (" + lPopulares.get(2).getQtdEmprestimo() + " empréstimos)\n");
            }else if(lPopulares.size() == 2){
                this.livrosPopulares.setText("Top 5 livros mais populares:\n" +
                        "     - " + lPopulares.get(0).getTitulo() + " (" + lPopulares.get(0).getQtdEmprestimo() + " empréstimos)\n" +
                        "     - "  + lPopulares.get(1).getTitulo() + " (" + lPopulares.get(1).getQtdEmprestimo() + " empréstimos)\n");

            }else if(lPopulares.size() == 1) {
                this.livrosPopulares.setText("Top 5 livros mais populares:\n" +
                        "- " + lPopulares.get(0).getTitulo() + " (" + lPopulares.get(0).getQtdEmprestimo() + " empréstimos)\n");
            }

        }
        else{
            this.livrosPopulares.setText("Top 5 livros mais populares: nenhum livro registrado");
        }


    }
    @FXML
    void voltarAction(ActionEvent event) {
        livrosPopulares.setText("");
        livrosReservados.setText("");
        livrosEmprestados.setText("");
        livrosAtrados.setText("");
        HelloApplication.changeScene("administrador");
    }


    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert hyperLink != null : "fx:id=\"hyperLink\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        assert livrosAtrados != null : "fx:id=\"livrosAtrados\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        assert livrosEmprestados != null : "fx:id=\"livrosEmprestados\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        assert livrosPopulares != null : "fx:id=\"livrosPopulares\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        assert livrosReservados != null : "fx:id=\"livrosReservados\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        assert voltar != null : "fx:id=\"voltar\" was not injected: check your FXML file 'relatorio-view.fxml'.";
        admUseCases = new AdmUseCases();
    }

}

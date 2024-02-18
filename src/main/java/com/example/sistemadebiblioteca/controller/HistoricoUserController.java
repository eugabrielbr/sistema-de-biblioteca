package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoricoUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField pesquisaText;

    @FXML
    private TableView<String> tabela;

    @FXML
    private Label textAlert;
    @FXML
    private TableColumn<String, String> titulo;
    @FXML
    private TableColumn<String, String> dataEmpres;
    @FXML
    private TableColumn<String, String> dataDevol;
    private String[] tituloL;
    private String[] emprestimo;
    private String[] devolucao;
    private String[] idL;


    @FXML
    private TableColumn<String, String> id;


    private List<Emprestimo> lista;



    public void convertListforArray(List<Emprestimo> lista){

        this.tituloL = new String[lista.size()];
        this.emprestimo  = new String[lista.size()];
        this.devolucao = new String[lista.size()];
        this.idL = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++){

            Emprestimo value = lista.get(i);


            this.tituloL[i] = value.getLivro().getTitulo();
            this.emprestimo[i] = String.valueOf(value.getDataEmprestimo());
            this.devolucao[i] = String.valueOf(value.getDataDevolucao());
            this.idL[i] = String.valueOf(value.getIDemprestimo());

        }

        ObservableList<String> listaTitulos = FXCollections.observableArrayList(tituloL);
        ObservableList<String> listaEmprestimos = FXCollections.observableArrayList(emprestimo);
        ObservableList<String> listaDevolucao = FXCollections.observableArrayList(devolucao);
        ObservableList<String> listaID = FXCollections.observableArrayList(idL);

        for (int i = 0; i < listaTitulos.size() && i < listaEmprestimos.size() && i <  listaDevolucao.size() && i <  listaID.size(); i++){
            ObservableList<String> item = FXCollections.observableArrayList(listaTitulos.get(i), listaEmprestimos.get(i),listaDevolucao.get(i),listaID.get(i));
            tabela.getItems().add(item.toString());
        }


    }
    @FXML
    void botaoBuscarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        try {
            Usuario user = DAO.getUsuarioDAO().findById(Integer.parseInt(pesquisaText.getText()));
            List<Emprestimo> listaHistorico = user.getHistorico();
            convertListforArray(listaHistorico);

        }catch (Exception e){
            System.out.println(e);
        }


    }

    @FXML
    void botaoVoltarAction(ActionEvent event) {

    }

    @FXML
    void tabelaAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert botaoBuscar != null : "fx:id=\"botaoBuscar\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert dataDevol != null : "fx:id=\"dataDevol\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert dataEmpres != null : "fx:id=\"dataEmpres\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert pesquisaText != null : "fx:id=\"pesquisaText\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert tabela != null : "fx:id=\"tabela\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert textAlert != null : "fx:id=\"textAlert\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'historicoUser-view.fxml'.";
        this.lista = new ArrayList<>();
        
    }

}

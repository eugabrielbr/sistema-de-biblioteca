package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.HistoricoUser;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Usuario;
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
    private TableView<HistoricoUser> tabela;

    @FXML
    private Label textAlert;
    @FXML
    private TableColumn<HistoricoUser, String> titulo;
    @FXML
    private TableColumn<HistoricoUser, String> dataEmpres;
    @FXML
    private TableColumn<HistoricoUser, String> dataDevol;
    //private String[] tituloL;
    //private String[] emprestimo;
    //private String[] devolucao;
    //private String[] idL;


    @FXML
    private TableColumn<HistoricoUser, String> id;

    private ObservableList<HistoricoUser> list;
    private List<Emprestimo> lista;



    public Integer convertListforArray(List<Emprestimo> lista){

        for (int i = 0; i < lista.size(); i++){

            this.list.add(new HistoricoUser(lista.get(i).getLivro().getTitulo(),String.valueOf(lista.get(i).getDataEmprestimo()),String.valueOf(lista.get(i).getDataDevolucao()),String.valueOf(lista.get(i).getIDemprestimo())));

        }


        titulo.setCellValueFactory(new PropertyValueFactory<HistoricoUser,String>("titulo"));
        dataEmpres.setCellValueFactory(new PropertyValueFactory<HistoricoUser,String>("dataEmprestimo"));
        dataDevol.setCellValueFactory(new PropertyValueFactory<HistoricoUser,String>("dataDevolucao"));
        id.setCellValueFactory(new PropertyValueFactory<HistoricoUser,String>("id"));
        tabela.setItems(list);

        return lista.size();
    }
    @FXML
    void botaoBuscarAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        tabela.getItems().clear();

        try {
            tabela.refresh();
            Usuario user = DAO.getUsuarioDAO().findById(Integer.parseInt(pesquisaText.getText()));
            List<Emprestimo> listaHistorico = user.getHistorico();

            convertListforArray(listaHistorico);
            pesquisaText.clear();

        }catch (Exception e){
            System.out.println(e);
        }


    }

    @FXML
    void botaoVoltarAction(ActionEvent event) {
        pesquisaText.clear();
        tabela.getItems().clear();
        HelloApplication.changeScene("gerenusu");
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
        this.list = FXCollections.observableArrayList();

        
    }

}

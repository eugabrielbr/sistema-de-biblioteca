package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.LocalDateNow;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.exceptions.usecases.UsuarioUseCasesExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.UsuarioUseCases;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RenovarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alertText;

    @FXML
    private Button botaoVoltar;
    @FXML
    private Hyperlink hyperLink;


    @FXML
    private ListView<String> listView;

    @FXML
    private Button renovarBotao;

    private String[] emprestimosAtivos;
    private List<Emprestimo> lista = new ArrayList<>();
    private UsuarioUseCases usuarioUseCases;


    public void convertListForArray(List<Emprestimo> lista){

        this.emprestimosAtivos = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++){

            Emprestimo value = lista.get(i);

            this.emprestimosAtivos[i] = value.getLivro().getTitulo() + " - ID: " + value.getLivro().getID();

        }

        listView.getItems().addAll(emprestimosAtivos);

    }


    @FXML
    void hyperLinkAction(ActionEvent event) throws IOException, ClassNotFoundException {
        listView.getItems().clear();
        listView.refresh();
        this.lista = DAO.getEmprestimoDAO().findByUser(LoginController.usuario.getID());
        convertListForArray(this.lista);
    }

    @FXML
    void botaoVoltarAction(ActionEvent event) {

        clear();
        HelloApplication.changeScene("usuario");
    }

    @FXML
    void renovarBotaoAction(ActionEvent event) throws IOException, ClassNotFoundException, UsuarioUseCasesExceptions, DAOExceptions {

        Integer index = this.listView.getSelectionModel().getSelectedIndex();

        if (index != null){
            alertText.setText("");
        }
        try{
            Emprestimo emprestimo = lista.get(index);
            usuarioUseCases.renovarLivros(emprestimo.getLivro().getID(),LoginController.usuario.getID(),LocalDateNow.localDateNow,DAO.getLivroDAO(),DAO.getUsuarioDAO(),DAO.getEmprestimoDAO());
            alertText.setText("Renovação Bem-sucedida! A nova data de devolução é: " + DAO.getEmprestimoDAO().findByIDlivroIDusuario(LoginController.usuario.getID(),emprestimo.getLivro().getID()).getDataDevolucao() );
            alertText.setStyle("-fx-text-fill: #69B00B;");
        }
        catch (UsuarioUseCasesExceptions e){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            Emprestimo emprestimo = lista.get(index);

            if (emprestimo.getFoiRenovado()){
                alertText.setText("Este livro já foi renovado!");
            }
            else{
                alertText.setText("Renovação mal-sucedida");
            }

        }
        catch (Exception x){
            alertText.setStyle("-fx-text-fill: #AE0001;");
            alertText.setText("Não foi possível realizar a renovação");
            System.out.println(x);
        }

    }
    public void clear(){

        listView.getItems().clear();
        alertText.setText("");

    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'renovar-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'renovar-view.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'renovar-view.fxml'.";
        assert renovarBotao != null : "fx:id=\"renovarBotao\" was not injected: check your FXML file 'renovar-view.fxml'.";
        assert hyperLink != null : "fx:id=\"hyperLink\" was not injected: check your FXML file 'renovar-view.fxml'.";
        this.usuarioUseCases = new UsuarioUseCases();
    }

}

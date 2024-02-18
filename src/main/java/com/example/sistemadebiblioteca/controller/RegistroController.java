package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAO;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistroController {

    private UsuarioDAO usuarioDAO;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alertText;

    @FXML
    private Button botaoCadastro;

    @FXML
    private TextField enderecoText;

    @FXML
    private TextField nomeText;

    @FXML
    private TextField senhaRegistroText;

    @FXML
    private Label erroRegistro;

    @FXML
    private TextField teltext;

    public void RegistroController() throws IOException, ClassNotFoundException {

        this.usuarioDAO = DAO.getUsuarioDAO();
    }

    @FXML
    private Button botaoVoltar;

    @FXML
    void botaoCadastroAction(ActionEvent event) throws IOException, ClassNotFoundException {


        alertText.setText("");
        erroRegistro.setText("");


        String name = nomeText.getText();
        String endereco = enderecoText.getText();
        String telefone = teltext.getText();
        String senha = senhaRegistroText.getText();


        if (senha.length() <= 8){

            if (!name.isEmpty() && !endereco.isEmpty() && !telefone.isEmpty() && !senha.isEmpty()) {

                Usuario usuario = new Usuario(name, endereco, telefone, senha);
                try {
                    Integer id = DAO.getUsuarioDAO().create(usuario);
                    erroRegistro.setText("Registro efetuado! LOGIN DE ACESSO: " + id);
                    clear();

                } catch (Exception e) {
                    erroRegistro.setText("Erro ao cadastrar. Tente novamente!");
                    System.out.println(e);
                }
            }
            else{

                alertText.setText("Preencha os campos!");

            }
        }
        else{

            alertText.setText("Senha inválida! Máximo de 8 caracteres.");
            senhaRegistroText.clear();
            erroRegistro.setText("");

        }


        }

    public void clear(){
        nomeText.clear();
        enderecoText.clear();
        teltext.clear();
        senhaRegistroText.clear();
    }
    @FXML
    void botaoVoltarAction(ActionEvent event) {

        clear();

        alertText.setText("");
        erroRegistro.setText("");

        //mudar cena
    }

    @FXML
    void initialize() {
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert botaoCadastro != null : "fx:id=\"botaoCadastro\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert enderecoText != null : "fx:id=\"enderecoText\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert nomeText != null : "fx:id=\"nomeText\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert senhaRegistroText != null : "fx:id=\"senhaRegistroText\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert teltext != null : "fx:id=\"teltext\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert erroRegistro != null : "fx:id=\"erroRegistro\" was not injected: check your FXML file 'registro-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'registro-view.fxml'.";
    }

}

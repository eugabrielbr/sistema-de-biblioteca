package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.dao.operadores.OperadoresDAO;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Operadores;
import com.example.sistemadebiblioteca.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoLogin;

    @FXML
    private Button botaoVoltar;


    @FXML
    private TextField loginTexto;

    @FXML
    private Label labelErro;

    @FXML
    private PasswordField senhaText;

    private UsuarioDAO usuarioDAO;
    private OperadoresDAO operadoresDAO;

    public static Operadores operador;

    public static Usuario usuario;


    public LoginController() throws IOException, ClassNotFoundException {

        this.usuarioDAO = DAO.getUsuarioDAO();
        this.operadoresDAO = DAO.getOperadoresDAO();

    }


    @FXML
    void botaoLoginAction(ActionEvent event) throws IOException, ClassNotFoundException, DAOExceptions {

        this.labelErro.setText("");


        try {

            if (HelloController.isAdm || HelloController.isBib) {


                Operadores operadorEncontrado = DAO.getOperadoresDAO().findById(Integer.parseInt(this.loginTexto.getText()));

                if (operadorEncontrado.getSenha().equals(this.senhaText.getText())){

                    operador = operadorEncontrado;

                    if (operadorEncontrado.getCargo().equals(1) && HelloController.isAdm){
                        clear();
                        HelloApplication.changeScene("administrador");
                    }
                    else if(operadorEncontrado.getCargo().equals(2) && HelloController.isBib){
                        clear();
                        HelloApplication.changeScene("bibliotecario");
                    }
                    else{
                        this.labelErro.setText("Usuário não encontrado!");
                    }

                }
                else{
                    this.labelErro.setText("Senha incorreta!");
                }
            }
            else if (HelloController.isLei){

                Usuario usuarioEncontrado = DAO.getUsuarioDAO().findById(Integer.parseInt(this.loginTexto.getText()));

                if (usuarioEncontrado.getSenha().equals(this.senhaText.getText())){

                    usuario = usuarioEncontrado;
                    HelloApplication.changeScene("usuario");
                    clear();
                }
                else{
                    this.labelErro.setText("Senha incorreta!");
                }
            }
        }
        catch (Exception e){
            this.labelErro.setText("Usuário não encontrado!");
            this.senhaText.clear();
            this.loginTexto.clear();
        }
    }

    public void clear(){
        this.senhaText.clear();
        this.loginTexto.clear();
        this.labelErro.setText("");
    }
    @FXML
    void botaoVoltarAction(ActionEvent event) {

        HelloController.isAdm = false;
        HelloController.isLei = false;
        HelloController.isBib = false;

        clear();

        HelloApplication.changeScene("hello");
    }


    @FXML
    void loginTextoAction(ActionEvent event) {
        //
    }

    @FXML
    void senhaTextoAction(ActionEvent event) {
        //
    }

    @FXML
    void initialize() {
        assert botaoLogin != null : "fx:id=\"botaoLogin\" was not injected: check your FXML file 'login-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'login-view.fxml'.";
        assert labelErro != null : "fx:id=\"labelErro\" was not injected: check your FXML file 'login-view.fxml'.";
        assert loginTexto != null : "fx:id=\"loginTexto\" was not injected: check your FXML file 'login-view.fxml'.";
        assert senhaText != null : "fx:id=\"senhaText\" was not injected: check your FXML file 'login-view.fxml'.";


    }

}

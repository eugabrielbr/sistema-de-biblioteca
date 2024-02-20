package com.example.sistemadebiblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.sistemadebiblioteca.HelloApplication;
import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Operadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistroOperadoresController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label alertText;

    @FXML
    private Button botaoCadastro;

    @FXML
    private Button botaoVoltar;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private Label erroRegistro;

    @FXML
    private TextField nomeText;
    private String[] options = {"Administrador","Bibliotecário"};

    @FXML
    private TextField senhaRegistroText;

    @FXML
    void botaoCadastroAction(ActionEvent event) throws IOException, ClassNotFoundException {



        try{

            if (choice.getValue().equals("Administrador") && !nomeText.getText().isEmpty() && !senhaRegistroText.getText().isEmpty()){

                String senha = senhaRegistroText.getText();

                if (senha.length() <= 8){

                    Operadores operadores = new Operadores(nomeText.getText(),1,senhaRegistroText.getText());
                    Integer id = DAO.getOperadoresDAO().create(operadores);
                    erroRegistro.setStyle("-fx-text-fill: #69B00B;");
                    erroRegistro.setText("Operador registrado!" + " Login de ACESSO: " + id);
                    clear();
                }
                else{
                    erroRegistro.setStyle("-fx-text-fill: #AE0001;");
                    erroRegistro.setText("A senha precisa ter menos que 8 carateres.");
                }

            }
            else if (choice.getValue().equals("Bibliotecário")&& !nomeText.getText().isEmpty() && !senhaRegistroText.getText().isEmpty()){


                String senha = senhaRegistroText.getText();

                if (senha.length() <= 8){

                    Operadores operadores = new Operadores(nomeText.getText(),2,senhaRegistroText.getText());
                    Integer id = DAO.getOperadoresDAO().create(operadores);
                    erroRegistro.setStyle("-fx-text-fill: #69B00B;");
                    erroRegistro.setText("Operador registrado!" + " Login de ACESSO: " + id);
                    clear();
                }
                else{
                    erroRegistro.setStyle("-fx-text-fill: #AE0001;");
                    erroRegistro.setText("A senha precisa ter menos que 8 carateres.");
                }


            }
            else{

                erroRegistro.setStyle("-fx-text-fill: #AE0001;");
                erroRegistro.setText("Preencha os campos!");

            }
        }catch (Exception e){
            erroRegistro.setStyle("-fx-text-fill: #AE0001;");
            erroRegistro.setText("Erro ao cadastrar!");
        }
    }

    public void clear(){
        nomeText.clear();
        senhaRegistroText.clear();
        choice.setValue("");
    }
    @FXML
    void botaoVoltarAction(ActionEvent event) {

        clear();
        erroRegistro.setText("");
        HelloApplication.changeScene("administrador");
    }

    @FXML
    void choiceAction(MouseEvent event) {
        //
    }

    @FXML
    void initialize() {
        assert alertText != null : "fx:id=\"alertText\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert botaoCadastro != null : "fx:id=\"botaoCadastro\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert botaoVoltar != null : "fx:id=\"botaoVoltar\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert choice != null : "fx:id=\"choice\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert erroRegistro != null : "fx:id=\"erroRegistro\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert nomeText != null : "fx:id=\"nomeText\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        assert senhaRegistroText != null : "fx:id=\"senhaRegistroText\" was not injected: check your FXML file 'registroOperadores-view.fxml'.";
        this.choice.getItems().addAll(this.options);
    }

}

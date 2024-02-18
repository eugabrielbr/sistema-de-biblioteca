package com.example.sistemadebiblioteca;

import com.example.sistemadebiblioteca.controller.InfoBookController;
import com.example.sistemadebiblioteca.controller.PesquisaLivroController;
import com.example.sistemadebiblioteca.model.Operadores;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private static Stage stage;
    private static Scene sceneInfoBook;
    private static Scene sceneHello;
    private static Scene sceneLogin;
    private static Scene sceneRegistro;
    private static Scene scenePesquisaLivro;
    private static Scene sceneUser;
    private static Scene sceneReserva;
    private static Scene sceneRenovar;
    private static Scene sceneBlib;
    private static Scene sceneEmpresDevol;
    private static Scene sceneRegistrarLivro;
    private static Scene sceneHelloAdm;
    private static Scene sceneRelatorio;
    private static Scene sceneGerenUsu;
    private static Scene sceneHistoricoUsu;
    @Override
    public void start( Stage primaryStage ) throws IOException {

        stage = primaryStage;

        Parent fxmlHello = new FXMLLoader().load(getClass().getResource("view/hello-view.fxml"));
        Parent fxmlLogin = new FXMLLoader().load(getClass().getResource("view/login-view.fxml"));
        Parent fxmlRegistro = new FXMLLoader().load(getClass().getResource("view/registro-view.fxml"));
        Parent fxmlPesquisaLivro = new FXMLLoader().load(getClass().getResource("view/pesquisaLivro-view.fxml"));
        Parent fxmlInfoBook = new FXMLLoader().load(getClass().getResource("view/infoBook-view.fxml"));
        Parent fxmlUser = new FXMLLoader().load(getClass().getResource("view/helloUser-view.fxml"));
        Parent fxmlReserva = new FXMLLoader().load(getClass().getResource("view/reserva-view.fxml"));
        Parent fxmlRenovar = new FXMLLoader().load(getClass().getResource("view/renovar-view.fxml"));
        Parent fxmlBlib = new FXMLLoader().load(getClass().getResource("view/helloBlib-view.fxml"));
        Parent fxmlEmpresDevol = new FXMLLoader().load(getClass().getResource("view/emprestimoDevolucao-view.fxml"));
        Parent fxmlRegistrarLivro = new FXMLLoader().load(getClass().getResource("view/registrarLivros-view.fxml"));
        Parent fxmlHelloAdm = new FXMLLoader().load(getClass().getResource("view/helloAdm-view.fxml"));
        Parent fxmlRelatorio= new FXMLLoader().load(getClass().getResource("view/relatorio-view.fxml"));
        Parent fxmlGerenUsu = new FXMLLoader().load(getClass().getResource("view/gerenUsu-view.fxml"));
        Parent fxmlHistoricoUsu = new FXMLLoader().load(getClass().getResource("view/historicoUser-view.fxml"));

        sceneHello = new Scene(fxmlHello,640,480);
        sceneLogin = new Scene(fxmlLogin,640,480);
        sceneRegistro = new Scene(fxmlRegistro,640,480);
        scenePesquisaLivro = new Scene(fxmlPesquisaLivro,640,480);
        sceneInfoBook = new Scene(fxmlInfoBook,335,600);
        sceneUser = new Scene(fxmlUser,640,480);
        sceneReserva = new Scene(fxmlReserva,640,480);
        sceneRenovar = new Scene(fxmlRenovar,640,480);
        sceneBlib = new Scene(fxmlBlib,640,480);
        sceneEmpresDevol = new Scene(fxmlEmpresDevol,640,480);
        sceneRegistrarLivro = new Scene(fxmlRegistrarLivro,640,480);
        sceneHelloAdm = new Scene(fxmlHelloAdm,640,480);
        sceneRelatorio = new Scene(fxmlRelatorio,640,480);
        sceneGerenUsu = new Scene(fxmlGerenUsu,640,480);
        sceneHistoricoUsu = new Scene(fxmlHistoricoUsu,640,480);

        stage.setScene(sceneHello);

        stage.show();


    }

    public static void changeScene(String scr){
        switch (scr){
            case "hello":
                stage.setScene(sceneHello);
                break;
            case "login":
                stage.setScene(sceneLogin);
                break;
            case "registro":
                stage.setScene(sceneRegistro);
                break;
            case "pesquisa":
                stage.setScene(scenePesquisaLivro);
                break;
            case "infoLivro":
                stage.setScene(sceneInfoBook);
                break;
            case "usuario":
                stage.setScene(sceneUser);
                break;
            case "reserva":
                stage.setScene(sceneReserva);
                break;
            case "renovar":
                stage.setScene(sceneRenovar);
                break;
            case "bibliotecario":
                stage.setScene(sceneBlib);
                break;
            case "empresdevol":
                stage.setScene(sceneEmpresDevol);
                break;
            case "registrarlivro":
                stage.setScene(sceneRegistrarLivro);
                break;
            case "administrador":
                stage.setScene(sceneHelloAdm);
                break;
            case "relatorio":
                stage.setScene(sceneRelatorio);
                break;
            case "gerenusu":
                stage.setScene(sceneGerenUsu);
                break;
            case "historicouser":
                stage.setScene(sceneHistoricoUsu);
                break;

        }
    }





    public static void main( String[] args ) {
        launch();
    }
}
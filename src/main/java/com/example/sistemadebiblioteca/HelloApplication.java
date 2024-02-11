package com.example.sistemadebiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private static Stage stage;
    private static Stage stageInfoLivro;
    private static Scene sceneHello;
    private static Scene sceneLogin;
    private static Scene sceneRegistro;
    @Override
    public void start( Stage primaryStage ) throws IOException {

        stage = primaryStage;
        stageInfoLivro = new Stage();

        Parent fxmlHello = new FXMLLoader().load(getClass().getResource("view/hello-view.fxml"));
        Parent fxmlLogin = new FXMLLoader().load(getClass().getResource("view/login-view.fxml"));
        Parent fxmlRegistro = new FXMLLoader().load(getClass().getResource("view/registro-view.fxml"));

        sceneHello = new Scene(fxmlHello,640,480);
        sceneLogin = new Scene(fxmlLogin,640,480);
        sceneRegistro = new Scene(fxmlRegistro,640,480);

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
            case "stageInfoLivro":
                stage = stageInfoLivro;
                stage.setScene(sceneHello);
                stage.show();
                break;


        }
    }





    public static void main( String[] args ) {
        launch();
    }
}
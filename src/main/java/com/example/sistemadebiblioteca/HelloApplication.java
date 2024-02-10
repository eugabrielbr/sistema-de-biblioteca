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
    private static Scene sceneHello;
    private static Scene sceneLogin;
    @Override
    public void start( Stage primaryStage ) throws IOException {

        stage = primaryStage;

        Parent fxmlHello = new FXMLLoader().load(getClass().getResource("view/hello-view.fxml"));
        Parent fxmlLogin = new FXMLLoader().load(getClass().getResource("view/login-view.fxml"));

        sceneHello = new Scene(fxmlHello,640,400);
        sceneLogin = new Scene(fxmlLogin,640,400);

        primaryStage.setScene(sceneHello);

        primaryStage.show();

    }

    public static void changeScene(String scr){
        switch (scr){
            case "hello":
                stage.setScene(sceneHello);
                break;
            case "login":
                stage.setScene(sceneLogin);
                break;
        }
    }





    public static void main( String[] args ) {
        launch();
    }
}
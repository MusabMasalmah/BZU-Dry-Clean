package com.example.database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;



public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LogInController login = new LogInController();
        login.ShowStage();
    }

    public static void main(String[] args) { launch();}

}


package com.example.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import javafx.scene.control.PasswordField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class LogInController {
    public static boolean isManager = true;

    @FXML
    private Text LoginText;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UID;


    public void ShowStage() {
        Stage stage1 = new Stage();
        FXMLLoader LogInLoader = new FXMLLoader(HelloApplication.class.getResource("LogIn.fxml"));
        stage1.setFullScreen(true);
        Scene LogIn_scene = null;
        try {
            LogIn_scene = new Scene(LogInLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.setTitle("Log In!");
        stage1.setScene(LogIn_scene);
        stage1.show();
    }
    @FXML
    void check_login(ActionEvent event) {

        int uid = Integer.parseInt(UID.getText());
        int PassWord = Integer.parseInt(Password.getText());


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000")) {
            String sql = "SELECT EPassword, EType FROM Employee WHERE EID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, uid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int storedPassword = resultSet.getInt("EPassword");
                String type = resultSet.getString("EType");

                // Compare the stored password with the entered password
                if (PassWord == storedPassword) {
                    if(type.equals("Accounter")){
                        isManager = false;
                    }
                    ((Stage) LoginText.getScene().getWindow()).close();
                    Stage IntStage = new Stage();
                    IntStage.setFullScreen(true);
                    FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
                    Scene Int_scene = new Scene(IntLoader.load());
                    IntStage.setScene(Int_scene);
                    IntStage.show();

                } else {
                    // Password is incorrect
                    LoginText.setText("Incorrect password!");
                }
            } else {
                // Employee ID not found
                LoginText.setText("Invalid employee ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void goSignUp(ActionEvent event) throws IOException {
        ((Stage) LoginText.getScene().getWindow()).close();
        Stage SignUpStage = new Stage();
        SignUpStage.setFullScreen(true);
        FXMLLoader SignUpLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene SignUp_scene = new Scene(SignUpLoader.load());
        SignUpStage.setTitle("SignUp!");
        SignUpStage.setScene(SignUp_scene);
        SignUpStage.show();
    }

}



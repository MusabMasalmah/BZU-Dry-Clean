package com.example.database;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SignUpController implements Initializable {

    @FXML
    private TextField EID;
    @FXML
    private TextField EName;
    @FXML
    private ComboBox<String> EGender;
    @FXML
    private TextField EPhone_Number;
    @FXML
    private TextField EEMAIL;
    @FXML
    private TextField EAddress;
    @FXML
    private ComboBox<String> EType;
    @FXML
    private PasswordField ECPassword;
    @FXML
    private PasswordField EPassword;
    @FXML
    private Text passtext;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> GenderList = FXCollections.observableArrayList("Male","Female");
        EGender.setItems(GenderList);

        ObservableList<String> TypeList = FXCollections.observableArrayList("Maneger","Accounter");
        EType.setItems(TypeList);

    }
    @FXML
    void AddEmp(ActionEvent event) {
        int flagpass = 0;
        int id = Integer.parseInt(EID.getText());
        String name = EName.getText();
        String gender = EGender.getValue();
        int phone = Integer.parseInt(EPhone_Number.getText());
        String email = EEMAIL.getText();
        String address = EAddress.getText();
        String type = EType.getValue();
        int epassword = Integer.parseInt(EPassword.getText());
        int ecpassword = Integer.parseInt(ECPassword.getText());

        if(epassword != ecpassword){
            flagpass = 1;
            EPassword.setText("");
            ECPassword.setText("");
            passtext.setText("The Password Not Equal");
        }
        else{
            flagpass = 0;
        }
        int flagUnique = 0;
        if(flagpass == 0) {
            String url = "jdbc:mysql://localhost:3306/DryClean";
            String username = "root";
            String password = "0000";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String sql = "INSERT INTO Employee(EID, EName, EGender, EPhone_Number, EEmail, EAddress, EType, ESalary, EPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                String sql1 = "SELECT COUNT(*) FROM Employee WHERE EID = ?";
                PreparedStatement statement1 = connection.prepareStatement(sql1);

                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, gender);
                statement.setInt(4, phone);
                statement.setString(5, email);
                statement.setString(6, address);
                statement.setString(7, type);
                statement.setInt(8, 0);
                statement.setInt(9, epassword);


                statement1.setInt(1, id);
                ResultSet resultSet = statement1.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        passtext.setText("ID is not unique.");
                        flagUnique = 1;
                    }
                    else{
                        flagUnique = 0;
                    }
                }

                if(flagUnique == 0) {
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new employee was inserted successfully.");
                    }

                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(flagUnique == 0) {
                EPhone_Number.setText("");
                EID.setText("");
                EName.setText("");
                EPassword.setText("");
                ECPassword.setText("");
                EEMAIL.setText("");
                EAddress.setText("");
                EType.setValue(null);
                EGender.setValue(null);
                passtext.setText("The Emolyee Inserted.");
            }
        }
    }

    @FXML
    void BackToLogin(ActionEvent event) throws IOException {
        ((Stage) ECPassword.getScene().getWindow()).close();
        Stage loginstage = new Stage();
        loginstage.setFullScreen(true);
        FXMLLoader LogInLoader = new FXMLLoader(HelloApplication.class.getResource("LogIn.fxml"));
        Scene LogIn_scene = new Scene(LogInLoader.load());
        loginstage.setTitle("Log In!");
        loginstage.setScene(LogIn_scene);
        loginstage.show();

    }

}
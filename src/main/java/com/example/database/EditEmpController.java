package com.example.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditEmpController implements Initializable {
    public static int uid;

    @FXML
    private TextArea AddressT;

    @FXML
    private TextArea EmailT;

    @FXML
    private TextArea GenderT;

    @FXML
    private TextArea IDT;

    @FXML
    private TextArea NameT;

    @FXML
    private TextArea TypeT;

    @FXML
    private TextArea PhoneT;

    @FXML
    private TextArea SalaryT;

    @FXML
    private Button BackBu;

    @FXML
    private Button DeleteBu;
    @FXML
    private TextArea InfoScreen;

    @FXML
    private Text DeleteNote;

    @FXML
    private TextField DeleteText;

    @FXML
    private Text UpdateNote;

    @FXML
    private TextField UpdateText;

    @Override
    public void initialize(URL Url, ResourceBundle resourceBundle) {
        int maxWidth = 15;
        String url = "jdbc:mysql://localhost:3306/DryClean";
        String username = "root";
        String password = "0000";

        // Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee")) {
            IDT.clear();
            NameT.clear();
            GenderT.clear();
            PhoneT.clear();
            EmailT.clear();
            AddressT.clear();
            TypeT.clear();
            SalaryT.clear();

            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("EID");
                String name = resultSet.getString("EName");
                String gender = resultSet.getString("EGender");
                int phone = resultSet.getInt("EPhone_Number");
                String email = resultSet.getString("EEmail");
                String address = resultSet.getString("EAddress");
                String type = resultSet.getString("EType");
                int salary = resultSet.getInt("ESalary");

                IDT.appendText(id+"\n");
                NameT.appendText(name+"\n");
                GenderT.appendText(gender+"\n");
                PhoneT.appendText(phone+"\n");
                EmailT.appendText(email+"\n");
                AddressT.appendText(address+"\n");
                TypeT.appendText(type+"\n");
                SalaryT.appendText(salary+"\n");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void BackEmp(ActionEvent event) throws IOException {
        ((Stage) IDT.getScene().getWindow()).close();
        Stage IntStage = new Stage();
        IntStage.setFullScreen(true);
        FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
        Scene Int_scene = new Scene(IntLoader.load());
        IntStage.setScene(Int_scene);
        IntStage.show();
    }

    @FXML
    void DeleteEmp(ActionEvent event) {

        int employeeId = Integer.parseInt(DeleteText.getText());
        String url = "jdbc:mysql://localhost:3306/DryClean";
        String username = "root";
        String password = "0000";
        // Execute the delete operation
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE EID = ?");) {

            statement.setInt(1, employeeId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Deletion successful
                DeleteNote.setText("Employee deleted successfully!");
            } else {
                // No rows affected, employee with the given ID not found
                DeleteNote.setText("Employee not found!");
            }
            DeleteText.setText("");
            initialize(null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateEmp(ActionEvent event) {
        uid = Integer.parseInt(UpdateText.getText());

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");

            // Create a prepared statement with a parameterized query
            String query = "SELECT COUNT(*) FROM Employee WHERE EID = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the value for the parameter
            statement.setInt(1, uid);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if the ID exists in the table
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    ((Stage) NameT.getScene().getWindow()).close();
                    Stage UUpdate = new Stage();
                    UUpdate.setFullScreen(true);
                    FXMLLoader UUpdateLoader = new FXMLLoader(HelloApplication.class.getResource("UpdateEmp.fxml"));
                    Scene UUpdate_scene = new Scene(UUpdateLoader.load());
                    UUpdate.setTitle("Log In!");
                    UUpdate.setScene(UUpdate_scene);
                    UUpdate.show();
                }
                else{
                    UpdateNote.setText("Employee doesn't exist");
                }
            }


            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

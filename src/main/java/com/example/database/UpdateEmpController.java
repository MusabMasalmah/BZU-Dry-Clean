package com.example.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateEmpController implements Initializable {

    @FXML
    private TextField UAddress;

    @FXML
    private TextField UCPassword;

    @FXML
    private TextField UEMAIL;

    @FXML
    private ComboBox<String> UGender;

    @FXML
    private TextField UName;

    @FXML
    private TextField UPassword;

    @FXML
    private TextField UPhone_Number;

    @FXML
    private ComboBox<String> UType;

    @FXML
    private TextField USalary;

    @FXML
    private Text passtext;

    @FXML
    void BackBu(ActionEvent event) throws IOException {
        ((Stage) passtext.getScene().getWindow()).close();
        Stage EUpdate = new Stage();
        EUpdate.setFullScreen(true);
        FXMLLoader EUpdateLoader = new FXMLLoader(HelloApplication.class.getResource("EditEmp.fxml"));
        Scene EUpdate_scene = new Scene(EUpdateLoader.load());
        EUpdate.setScene(EUpdate_scene);
        EUpdate.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> GenderList = FXCollections.observableArrayList("Male","Female");
        UGender.setItems(GenderList);

        ObservableList<String> TypeList = FXCollections.observableArrayList("Maneger","Accounter");
        UType.setItems(TypeList);

        //System.out.println(uid);

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT * FROM Employee WHERE EID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, EditEmpController.uid);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UName.setText(resultSet.getString("EName"));
                UGender.setValue(resultSet.getString("EGender"));
                UType.setValue(resultSet.getString("EType"));
                UEMAIL.setText(resultSet.getString("EEmail"));
                UAddress.setText(resultSet.getString("EAddress"));
                UPhone_Number.setText(resultSet.getString("EPhone_Number"));
                USalary.setText(resultSet.getString("ESalary"));
                UPassword.setText(resultSet.getString("EPassword"));
                UCPassword.setText(resultSet.getString("EPassword"));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void UpdateBu(ActionEvent event) {
        int flagpass = 0;
        int id = EditEmpController.uid;
        String name = UName.getText();
        String gender = UGender.getValue();
        int phone = Integer.parseInt(UPhone_Number.getText());
        String email = UEMAIL.getText();
        String address = UAddress.getText();
        String type = UType.getValue();
        int salary = Integer.parseInt(USalary.getText());
        int epassword = Integer.parseInt(UPassword.getText());
        int ecpassword = Integer.parseInt(UCPassword.getText());

        if(epassword != ecpassword){
            flagpass = 1;
            UPassword.setText("");
            UCPassword.setText("");
            passtext.setText("The Password Not Equal");
        }
        else{
            flagpass = 0;
        }

        if(flagpass == 0) {
            String url = "jdbc:mysql://localhost:3306/DryClean";
            String username = "root";
            String password = "0000";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                String sql = "UPDATE Employee SET EName = ?, EGender = ?, EPhone_Number = ?, EEmail = ?, EAddress = ?, EType = ?, ESalary = ?, EPassword = ? WHERE EID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, name);
                statement.setString(2, gender);
                statement.setInt(3, phone);
                statement.setString(4, email);
                statement.setString(5, address);
                statement.setString(6, type);
                statement.setInt(7, salary);
                statement.setInt(8, epassword);
                statement.setInt(9, id);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new employee was inserted successfully.");
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            UPhone_Number.setText("");
            UName.setText("");
            UPassword.setText("");
            UCPassword.setText("");
            UEMAIL.setText("");
            UAddress.setText("");
            USalary.setText("");
            UType.setValue(null);
            UGender.setValue(null);
            passtext.setText("Update Done.");
        }
    }
}

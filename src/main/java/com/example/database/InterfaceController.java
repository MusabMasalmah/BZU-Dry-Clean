package com.example.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {
    @FXML
    private Button EmpBu;

    @FXML
    private Button ReportBu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!LogInController.isManager) {
            EmpBu.setVisible(false);
            ReportBu.setVisible(false);
        }
    }

    @FXML
    void CustomerAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Stage stage = new Stage();
        stage.setFullScreen(true);
        Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void EmployeesAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Stage EUpdate = new Stage();
        EUpdate.setFullScreen(true);
        FXMLLoader EUpdateLoader = new FXMLLoader(HelloApplication.class.getResource("EditEmp.fxml"));
        Scene EUpdate_scene = new Scene(EUpdateLoader.load());
        EUpdate.setScene(EUpdate_scene);
        EUpdate.show();
    }

    @FXML
    void EquipmentAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Equipment.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.setFullScreen(true);
        stage1.show();
    }

    @FXML
    void ItemAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Item.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void ReportAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Stage stage = new Stage();
        stage.setFullScreen(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Report!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SupplierAc(ActionEvent event) throws IOException {
        ((Stage) EmpBu.getScene().getWindow()).close();
        Stage stage = new Stage();
        stage.setFullScreen(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Supplier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 655, 475);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}

package com.example.database;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EquipmentController implements Initializable {

    @FXML
    private TableColumn<Equipment, Integer> col_cost;

    @FXML
    private TableColumn<Equipment, Integer> col_ID;

    @FXML
    private TableColumn<Equipment, String> col_name;

    @FXML
    private TableColumn<Equipment, String> col_quality;

    @FXML
    private TableColumn<Equipment, Integer> col_sid;

    @FXML
    private TableView<Equipment> table_Equipment;

    @FXML
    private TextField txt_ID;

    @FXML
    private TextField txt_NAME;

    @FXML
    private TextField txt_cost;

    @FXML
    private TextField txt_quality;

    @FXML
    private TextField txt_sid;

    ObservableList<Equipment> List_Equipment;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void Add_eq(ActionEvent event) {

        conn = mySqlConnect.ConnectDB();
        String sql = "insert into Equipment (EqID,EqName,EqQuality,EqCost,SuID)values(?,?,?,?,?)";
        try{

            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.setString(2, txt_NAME.getText());
            pst.setString(3, txt_quality.getText());
            pst.setString(4, txt_cost.getText());
            pst.setString(5, txt_sid.getText());

            pst.execute();


           //  JOptionPane.showMessageDialog(null,"Item Add success");
            //UpdateTable();

        }catch (Exception e){
            // JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    void Clear(ActionEvent event) {
        txt_ID.clear();
        txt_cost.clear();
        txt_NAME.clear();
        txt_quality.clear();
        txt_sid.clear();
    }

    @FXML
    void Delete_eq(ActionEvent event) {
        conn = mySqlConnect.ConnectDB();
        String sql = "Delete from Equipment where EqID = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            // JOptionPane.showMessageDialog(null,"Delete");
            UpdateTable();

        } catch (SQLException e) {
            //  JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    void Select_Equipment(MouseEvent event) {
        index = table_Equipment.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_ID.setText(col_ID.getCellData(index).toString());
        txt_NAME.setText(col_name.getCellData(index).toString());
        txt_cost.setText(col_cost.getCellData(index).toString());
        txt_quality.setText(col_quality.getCellData(index).toString());
        txt_sid.setText(col_sid.getCellData(index).toString());
    }

    @FXML
    void Update_eq(ActionEvent event) {

        try {
            conn = mySqlConnect.ConnectDB();
            String value1 = txt_ID.getText();
            String value2 = txt_NAME.getText();
            String value3 = txt_cost.getText();
            String value4 = txt_quality.getText();
            String value5 = txt_sid.getText();


            String sql = "Update Item set EqID = '" +value1+"',EqName = '" +value2+"',EqCost = '"+value3+
                    "',EqQuality = '"+value4+"',SuID = '"+value5+ "'where  EqID = ' "+value1+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            // JOptionPane.showMessageDialog(null,"Update");
            UpdateTable();
        }
        catch (Exception e){
            // JOptionPane.showMessageDialog(null,e);
        }
    }
    public void UpdateTable(){
        col_ID.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("EqID"));
        col_cost.setCellValueFactory(new PropertyValueFactory<Equipment,Integer >("EqCost"));
        col_name.setCellValueFactory(new PropertyValueFactory<Equipment,String >("EqName"));
        col_sid.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("SuID"));
        col_quality.setCellValueFactory(new PropertyValueFactory<Equipment,String >("EqQuality"));

        List_Equipment = mySqlConnect.getDataEquipment();
        table_Equipment.setItems(List_Equipment);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        ((Stage) txt_ID.getScene().getWindow()).close();
        Stage IntStage = new Stage();
        IntStage.setFullScreen(true);
        FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
        Scene Int_scene = new Scene(IntLoader.load());
        IntStage.setScene(Int_scene);
        IntStage.show();
    }
}
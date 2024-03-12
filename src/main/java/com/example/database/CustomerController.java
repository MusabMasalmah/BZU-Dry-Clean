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
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerController implements Initializable {


    @FXML
    private TableView<Customer> table_customer;

    @FXML
    private TableColumn<Customer, Integer> col_id;

    @FXML
    private TableColumn<Customer, String> col_CName;

    @FXML
    private TableColumn<Customer, Integer> col_CPhone_Number;

    @FXML
    private TableColumn<Customer, String> col_email;

    @FXML
    private TableColumn<Customer, String> col_address;



    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_CName;

    @FXML
    private TextField txt_CPhone_Number;

    ObservableList<Customer> ListC;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;

    PreparedStatement pst = null;

    public void Clear(){

        txt_id.clear();
        txt_CName.clear();
        txt_CPhone_Number.clear();
        txt_email.clear();
        txt_address.clear();

    }
    public void Add_Customer(){

        conn = mySqlConnect.ConnectDB();
        String sql = "insert into Customer (CID,CName,CPhone_Number,CEmail ,CAddress)values(?,?,?,?,?)";
        try{

            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_CName.getText());
            pst.setString(3, txt_CPhone_Number.getText());
            pst.setString(4, txt_email.getText());
            pst.setString(5, txt_address.getText());
            pst.execute();


            JOptionPane.showMessageDialog(null,"Customer Add success");
            UpdateTable();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);

        }
    }

    public void Delete_Customer(){
        conn = mySqlConnect.ConnectDB();
        String sql = "Delete from Customer where CID = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Delete");
            UpdateTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public void Update_Customer(){

        try {
            conn = mySqlConnect.ConnectDB();
            String value1 = txt_id.getText();
            String value2 = txt_CName.getText();
            String value3 = txt_CPhone_Number.getText();
            String value4 = txt_email.getText();
            String value5 = txt_address.getText();

            String sql = "Update Customer set CID = '" +value1+"',CName = '" +value2+"',CPhone_Number = '"+value3+
                    "',CEmail = '"+value4+"',CAddress = '"+value5+"'where  CID = ' "+value1+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Update");
            UpdateTable();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("id"));
        col_CName.setCellValueFactory(new PropertyValueFactory<Customer,String>("Name"));
        col_CPhone_Number.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("Phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));
        col_address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));



        ListC = mySqlConnect.getDataCustomer();
        table_customer.setItems(ListC);

    }


    public void initialize(URL url, ResourceBundle rb){
        UpdateTable();

    }
    @FXML
    public void Select_Customer(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_customer.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_CName.setText(col_CName.getCellData(index).toString());
        txt_CPhone_Number.setText(col_CPhone_Number.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_address.setText(col_address.getCellData(index).toString());
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        ((Stage) txt_CName.getScene().getWindow()).close();
        Stage IntStage = new Stage();
        IntStage.setFullScreen(true);
        FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
        Scene Int_scene = new Scene(IntLoader.load());
        IntStage.setScene(Int_scene);
        IntStage.show();
    }
}



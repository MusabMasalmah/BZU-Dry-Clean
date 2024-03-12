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

public class ItemController implements Initializable {


    @FXML
    private TableColumn<Item, Integer> col_cost;

    @FXML
    private TableColumn<Item, Integer> col_id;

    @FXML
    private TableColumn<Item, Integer> col_iid;

    @FXML
    private TableColumn<Item, String> col_type;

    @FXML
    private TableView<Item> table_item;

    @FXML
    private TextField txt_ID;

    @FXML
    private TextField txt_cid;

    @FXML
    private TextField txt_cost;

    @FXML
    private TextField txt_type;




    ObservableList<Item> List_Item;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;

    PreparedStatement pst = null;

    public void Clear(){

        txt_ID.clear();
        txt_type.clear();
        txt_cost.clear();
        txt_cid.clear();

    }
    public void Add_Item(){

        conn = mySqlConnect.ConnectDB();
        String sql = "insert into Item (IID,IType,ICost,CID)values(?,?,?,?)";
        try{

            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.setString(2, txt_type.getText());
            pst.setString(3, txt_cost.getText());
            pst.setString(4, txt_cid.getText());

            pst.execute();


            JOptionPane.showMessageDialog(null,"Item Add success");
            UpdateTable();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);

        }
    }
    public void Delete_Item(){
        conn = mySqlConnect.ConnectDB();
        String sql = "Delete from Item where IID = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Delete");
            UpdateTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public void Update_Item(){

        try {
            conn = mySqlConnect.ConnectDB();
            String value1 = txt_ID.getText();
            String value2 = txt_type.getText();
            String value3 = txt_cost.getText();
            String value4 = txt_cid.getText();


            String sql = "Update Item set IID = '" +value1+"',IType = '" +value2+"',ICost = '"+value3+
                    "',CID = '"+value4+ "'where  IID = ' "+value1+"' ";
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
        col_iid.setCellValueFactory(new PropertyValueFactory<Item,Integer>("IID"));
        col_cost.setCellValueFactory(new PropertyValueFactory<Item,Integer >("ICost"));
        col_type.setCellValueFactory(new PropertyValueFactory<Item,String >("IType"));
        col_id.setCellValueFactory(new PropertyValueFactory<Item,Integer>("CID"));

        List_Item = mySqlConnect.getDataItem();
        table_item.setItems(List_Item);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
    public void Select_Item(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_item.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_ID.setText(col_iid.getCellData(index).toString());
        txt_type.setText(col_type.getCellData(index).toString());
        txt_cost.setText(col_cost.getCellData(index).toString());
        txt_cid.setText(col_id.getCellData(index).toString());

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

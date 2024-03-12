package com.example.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class viewSuppliers implements Initializable {
    @FXML
    private TableView<Supplier> SuppliersTable;
    @FXML
    private TableColumn<Supplier,Integer> SuIDtv;
    @FXML
    private TableColumn<Supplier,String> SUNametv;
    @FXML
    private TableColumn<Supplier, Long> phonetv;
    @FXML
    private TableColumn<Supplier,String> Adrresstv;
    @FXML
    private TableColumn<Supplier,String> emailtv;
    @FXML
    private TextField suIDtext;
    @FXML
    private TextField nametext;
    @FXML
    private TextField phonetext;
    @FXML
    private TextField addresstext;
    @FXML
    private TextField emailtext;
    @FXML
    private Button addsubt;
    @FXML
    private TextField idtxt1;
    @FXML
    private TextField nametxt1;
    @FXML
    private TextField phonetxt1;
    @FXML
    private TextField addtxt1;
    @FXML
    private TextField emailtxt1;
    @FXML
    private Button ubdateBT;
    @FXML
    private TextField idtxt2;
    @FXML
    private TextField nametxt2;
    @FXML
    private TextField phonetxt2;
    @FXML
    private TextField addtxt2;
    @FXML
    private TextField emailtxt2;
    @FXML
    private Button deleteBT;

    private ObservableList<Supplier> data = FXCollections.observableArrayList();

    public viewSuppliers() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String sql = "select * from Supplier";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SuIDtv.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SuID"));
        SUNametv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuName"));
        phonetv.setCellValueFactory(new PropertyValueFactory<Supplier, Long>("SuPhone_Number"));
        Adrresstv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuAddress"));
        emailtv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuEmail"));

        SuppliersTable.setItems(data);



    }

    public void insertProduct(ActionEvent e)throws IOException {
        String suid = suIDtext.getText();
        String suname = nametext.getText();
        String suphone = phonetext.getText();
        String suadrress = addresstext.getText();
        String suemail = emailtext.getText();

        int suidint = Integer.parseInt(suid);
        long suphonelong = Integer.parseInt(suphone);

        Supplier sup = new Supplier();
        sup.setSuID(suidint);
        sup.setSuName(suname);
        sup.setSuPhone_Number(suphonelong);
        sup.setSuAddress(suadrress);
        sup.setSuEmail(suemail);

        int status = SupplierDP.save(sup);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supplier Insert");
            alert.setHeaderText("information Dialog");
            alert.setContentText("data saved successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Supplier Insert");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Unable to save data");
            alert.showAndWait();



        }
        suIDtext.setText("");
        nametext.setText("");
        phonetext.setText("");
        addresstext.setText("");
        emailtext.setText("");
        SuppliersTable.getItems().clear();
        try {
            String sql = "select * from Supplier";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception i) {
            throw new RuntimeException(i);
        }
        SuIDtv.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SuID"));
        SUNametv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuName"));
        phonetv.setCellValueFactory(new PropertyValueFactory<Supplier, Long>("SuPhone_Number"));
        Adrresstv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuAddress"));
        emailtv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuEmail"));

        SuppliersTable.setItems(data);


    }

    public void updateProduct(ActionEvent e){
        String sid = idtxt1.getText();
        int id = Integer.parseInt(sid);

        String phone = phonetxt1.getText();
        int pp = Integer.parseInt(phone);

        String name = nametxt1.getText();
        String address = addtxt1.getText();
        String email = emailtxt1.getText();


        Supplier p = new Supplier();

        p.setSuID(id);
        p.setSuName(name);
        p.setSuAddress(address);
        p.setSuEmail(email);
        p.setSuPhone_Number(pp);

        int status = SupplierDP.update(p);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Update");
            alert.setHeaderText("information Dialog");
            alert.setContentText("data Ubdated successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Ubdate");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Unable to Ubdate data");
            alert.showAndWait();

        }
        idtxt1.setText("");
        nametxt1.setText("");
        phonetxt1.setText("");
        addtxt1.setText("");
        emailtxt1.setText("");

        SuppliersTable.getItems().clear();

        try {
            String sql = "select * from Supplier";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception i) {
            throw new RuntimeException(i);
        }
        SuIDtv.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SuID"));
        SUNametv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuName"));
        phonetv.setCellValueFactory(new PropertyValueFactory<Supplier, Long>("SuPhone_Number"));
        Adrresstv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuAddress"));
        emailtv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuEmail"));

        SuppliersTable.setItems(data);



    }
    public void delete(ActionEvent e) throws IOException, ParseException {

        String sid = idtxt2.getText();
        int id = Integer.parseInt(sid);



        int status = SupplierDP.delete(id);
        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Update");
            alert.setHeaderText("information Dialog");
            alert.setContentText("data Ubdated successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Ubdate");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Unable to Ubdate data");
            alert.showAndWait();

        }

        SuppliersTable.getItems().clear();
        try {
            String sql = "select * from Supplier";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                data.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception i) {
            throw new RuntimeException(i);
        }
        SuIDtv.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SuID"));
        SUNametv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuName"));
        phonetv.setCellValueFactory(new PropertyValueFactory<Supplier, Long>("SuPhone_Number"));
        Adrresstv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuAddress"));
        emailtv.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SuEmail"));

        SuppliersTable.setItems(data);

    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        ((Stage) suIDtext.getScene().getWindow()).close();
        Stage IntStage = new Stage();
        IntStage.setFullScreen(true);
        FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
        Scene Int_scene = new Scene(IntLoader.load());
        IntStage.setScene(Int_scene);
        IntStage.show();
    }
}

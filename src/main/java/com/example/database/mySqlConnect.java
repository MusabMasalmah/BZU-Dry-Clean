package com.example.database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class mySqlConnect {

    Connection conn = null;
    public static Connection ConnectDB () {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/DryClean", "root", "0000");

            // JOptionPane.showMessageDialog(null,"ConnectionEstablish");
            return conn;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,e);

            return null;
        }
    }
    public static ObservableList<Customer> getDataCustomer(){

        Connection conn = ConnectDB();
        ObservableList<Customer> List = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("Select * From Customer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){


                List.add(new Customer(Integer.parseInt(rs.getString("CID")),rs.getString("CName"),Integer.parseInt(rs.getString("CPhone_Number")),rs.getString("CEmail") , rs.getString("CAddress")));
            }
        }catch (Exception e){

        }

        return List;
    }

    public static ObservableList<Item> getDataItem(){

        Connection conn = ConnectDB();
        ObservableList<Item> List1 = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("Select * From Item");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){


                List1.add(new Item(Integer.parseInt(rs.getString("IID")),rs.getString("IType") ,Integer.parseInt(rs.getString("ICost")), Integer.parseInt(rs.getString("CID"))));
            }
        }catch (Exception e){

        }

        return List1;
    }

    public static ObservableList<Equipment> getDataEquipment(){

        Connection conn = ConnectDB();
        ObservableList<Equipment> List = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("Select * From Equipment");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){


                List.add(new Equipment(Integer.parseInt(rs.getString("EqID")),rs.getString("EqName"),rs.getString("EqQuality"),Integer.parseInt(rs.getString("EqCost")) , Integer.parseInt(rs.getString("SuID"))));
            }
        }catch (Exception e){

        }

        return List;
    }


}

package com.example.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierDP {
    public static int save(Supplier s) {
        int st = 0;

        try {
            String sql = "insert into Supplier (SuID, SuName, SuPhone_Number, SuAddress, SuEmail) VALUES (?,?,?,?,?)";
            Connection con = com.example.database.ConnectionDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, s.getSuID());
            preparedStatement.setString(2, s.getSuName());
            preparedStatement.setLong(3, s.getSuPhone_Number());
            preparedStatement.setString(4, s.getSuAddress());
            preparedStatement.setString(5, s.getSuName());

            st = preparedStatement.executeUpdate();
            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;
    }
    public static int update(Supplier s) {
        int st = 0;

        try {
            String sql = "UPDATE Supplier SET  SuName=?, SuPhone_Number=?, SuAddress=?, SuEmail=? WHERE SuID=?";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, s.getSuName());
            preparedStatement.setLong(2, s.getSuPhone_Number());
            preparedStatement.setString(3, s.getSuAddress());
            preparedStatement.setString(4, s.getSuEmail());
            preparedStatement.setInt(5, s.getSuID());

            st = preparedStatement.executeUpdate();
            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;

    }
    public static int delete(int id) {
        int st = 0;

        try {
            String sql = " delete from Supplier where SuID=? ";
            Connection con = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            st = preparedStatement.executeUpdate();
            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;

    }

}

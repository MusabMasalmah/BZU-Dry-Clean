package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
//import com.mysql.cj.xdevapi.PreparableStatement;
import java.util.List;
import java.sql.DriverManager;
import java.sql.*;

public class ConnectionDB {

    private static String dbUsername = "root"; // database username
    private static String dbPassword = "0000"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "DryClean"; // database on mysql to connect to
    public static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + URL + ":" + port + "/" + dbName, dbUsername,
                    dbPassword);
            //System.out.println("coneccted");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}

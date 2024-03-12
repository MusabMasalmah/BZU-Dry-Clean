package com.example.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private TextArea CID;

    @FXML
    private TextArea CNAME;

    @FXML
    private TextArea ECOST1;

    @FXML
    private TextArea EID1;

    @FXML
    private TextArea ENAME1;

    @FXML
    private TextArea ICOST;

    @FXML
    private TextArea IID;

    @FXML
    private TextArea ITYPE;

    @FXML
    private TextField NOC;

    @FXML
    private TextField NOE;

    @FXML
    private TextField NOEQ1;

    @FXML
    private TextField NOI;

    @FXML
    private TextField NOS;

    @FXML
    private TextArea SID;

    @FXML
    private TextArea SName;

    @FXML
    private TextField TOTALCOST;

    @FXML
    private TextField TOTALCOSTOQ1;

    @FXML
    private TextField TotalS;

    @FXML
    private TextArea eid;

    @FXML
    private TextArea ename;

    @FXML
    private TextArea esalary;

    @FXML
    private TextField PROFIT;

    @FXML
    private TextField TOTALINCOM;

    @FXML
    private TextField TOTALOUTCOME;



    @Override
    public void initialize(URL Url, ResourceBundle resourceBundle) {
        int totalSalaries = 0;
        int totalcost = 0;
        int eqcost = 0;

        int maxWidth = 15;
        String url = "jdbc:mysql://localhost:3306/DryClean";
        String username = "root";
        String password = "0000";

        // Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee")) {
            eid.clear();
            ename.clear();
            esalary.clear();

            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("EID");
                String name = resultSet.getString("EName");
                int salary = resultSet.getInt("ESalary");

                eid.appendText(id + "\n");
                ename.appendText(name + "\n");
                esalary.appendText(salary + "\n");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT COUNT(*) AS TotalEmployees FROM Employee";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int totalEmployees = resultSet.getInt("TotalEmployees");
                NOE.setText(String.valueOf(totalEmployees));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT SUM(ESalary) AS TotalSalaries FROM Employee";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                totalSalaries = resultSet.getInt("TotalSalaries");
                TotalS.setText(String.valueOf(totalSalaries));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Item")) {
            IID.clear();
            ITYPE.clear();
            ICOST.clear();

            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("IID");
                String type = resultSet.getString("IType");
                int cost = resultSet.getInt("ICost");

                IID.appendText(id + "\n");
                ITYPE.appendText(type + "\n");
                ICOST.appendText(cost + "\n");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT COUNT(*) AS TotalItem FROM Item";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int totalEmployees = resultSet.getInt("TotalItem");
                NOI.setText(String.valueOf(totalEmployees));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT SUM(ICost) AS TotalCost FROM Item";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                totalcost = resultSet.getInt("TotalCost");
                TOTALCOST.setText(String.valueOf(totalcost));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer")) {
            CID.clear();
            CNAME.clear();


            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("CID");
                String name = resultSet.getString("CName");


                CID.appendText(id + "\n");
                CNAME.appendText(name + "\n");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT COUNT(*) AS TotalCustomer FROM Customer";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int totalEmployees = resultSet.getInt("TotalCustomer");
                NOC.setText(String.valueOf(totalEmployees));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


// Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Supplier")) {
            SID.clear();
            SName.clear();


            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("SuID");
                String name = resultSet.getString("SuName");


                SID.appendText(id + "\n");
                SName.appendText(name + "\n");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT COUNT(*) AS TotalSupplier FROM Supplier";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int totalEmployees = resultSet.getInt("TotalSupplier");
                NOS.setText(String.valueOf(totalEmployees));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Execute the SQL query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Equipment")) {
            EID1.clear();
            ENAME1.clear();
            ECOST1.clear();

            // Iterate over the results and append to the TextArea
            while (resultSet.next()) {
                int id = resultSet.getInt("EqID");
                String name = resultSet.getString("EqName");
                int cost = resultSet.getInt("EqCost");

                EID1.appendText(id + "\n");
                ENAME1.appendText(name + "\n");
                ECOST1.appendText(cost + "\n");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT COUNT(*) AS TotalEquipment FROM Equipment";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int totalEmployees = resultSet.getInt("TotalEquipment");
                NOEQ1.setText(String.valueOf(totalEmployees));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DryClean", "root", "0000");
            String query = "SELECT SUM(EqCost) AS TotalEquipment FROM Equipment";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                eqcost = resultSet.getInt("TotalEquipment");
                TOTALCOSTOQ1.setText(String.valueOf(eqcost));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    float outcome = eqcost + totalSalaries;
    float income = totalcost;
    float prof = income - outcome;

    PROFIT.setText(String.valueOf(prof));

    TOTALINCOM.setText(String.valueOf(income));

    TOTALOUTCOME.setText(String.valueOf(outcome));



    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        ((Stage) TOTALOUTCOME.getScene().getWindow()).close();
        Stage IntStage = new Stage();
        IntStage.setFullScreen(true);
        FXMLLoader IntLoader = new FXMLLoader(HelloApplication.class.getResource("Interface.fxml"));
        Scene Int_scene = new Scene(IntLoader.load());
        IntStage.setScene(Int_scene);
        IntStage.show();
    }
}



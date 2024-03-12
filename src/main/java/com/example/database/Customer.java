package com.example.database;


public class Customer {


    int CID, CPhone_Number;
    String CName, CEmail, CAddress;

    public Customer(int CID, String CName, int CPhone_Number, String CEmail ,String CAddress) {
        this.CID = CID;
        this.CName = CName;
        this.CPhone_Number = CPhone_Number;
        this.CEmail = CEmail;
        this.CAddress = CAddress;

    }



    public int getId() {
        return CID;
    }

    public void setId(int id) {
        this.CID = CID;
    }

    public String getName() {
        return CName;
    }

    public void setName(String name) {
        this.CName = CName;
    }

    public String getEmail() {
        return CEmail;
    }

    public void setEmail(String email) {
        this.CEmail = CEmail;
    }

    public String getAddress() {
        return CAddress;
    }

    public void setAddress(String address) {
        this.CAddress = CAddress;
    }

    public int getPhone() {
        return CPhone_Number;
    }

    public void setPhone(int phone) {
        this.CPhone_Number = CPhone_Number;
    }

}
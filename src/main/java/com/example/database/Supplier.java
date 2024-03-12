package com.example.database;
public class Supplier {
    private int SuID;
    private String SuName;
    private long SuPhone_Number;
    private String SuAddress;
    private String SuEmail;

    public Supplier(int suID, String suName, long suPhone_Number, String suAddress, String suEmail) {
        SuID = suID;
        SuName = suName;
        SuPhone_Number = suPhone_Number;
        SuAddress = suAddress;
        SuEmail = suEmail;
    }

    public Supplier() {

    }

    public int getSuID() {
        return SuID;
    }

    public void setSuID(int suID) {
        SuID = suID;
    }

    public String getSuName() {
        return SuName;
    }

    public void setSuName(String suName) {
        SuName = suName;
    }

    public long getSuPhone_Number() {
        return SuPhone_Number;
    }

    public void setSuPhone_Number(long suPhone_Number) {
        SuPhone_Number = suPhone_Number;
    }

    public String getSuAddress() {
        return SuAddress;
    }

    public void setSuAddress(String suAddress) {
        SuAddress = suAddress;
    }

    public String getSuEmail() {
        return SuEmail;
    }

    public void setSuEmail(String suEmail) {
        SuEmail = suEmail;
    }
}

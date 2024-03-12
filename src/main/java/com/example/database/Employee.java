package com.example.database;

public class Employee {
    int EID,EPhone_Number,ESalary,EPassword,SID;
    String EName,EGender,EEmail,EAddress,EType;

    public Employee(int EID, int EPhone_Number, int ESalary, int EPassword, int SID, String EName, String EGender, String EEmail, String EAddress, String EType) {
        this.EID = EID;
        this.EPhone_Number = EPhone_Number;
        this.ESalary = ESalary;
        this.EPassword = EPassword;
        this.SID = SID;
        this.EName = EName;
        this.EGender = EGender;
        this.EEmail = EEmail;
        this.EAddress = EAddress;
        this.EType = EType;
    }

    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
    }

    public int getEPhone_Number() {
        return EPhone_Number;
    }

    public void setEPhone_Number(int EPhone_Number) {
        this.EPhone_Number = EPhone_Number;
    }

    public int getESalary() {
        return ESalary;
    }

    public void setESalary(int ESalary) {
        this.ESalary = ESalary;
    }

    public int getEPassword() {
        return EPassword;
    }

    public void setEPassword(int EPassword) {
        this.EPassword = EPassword;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public String getEGender() {
        return EGender;
    }

    public void setEGender(String EGender) {
        this.EGender = EGender;
    }

    public String getEEmail() {
        return EEmail;
    }

    public void setEEmail(String EEmail) {
        this.EEmail = EEmail;
    }

    public String getEAddress() {
        return EAddress;
    }

    public void setEAddress(String EAddress) {
        this.EAddress = EAddress;
    }

    public String getEType() {
        return EType;
    }

    public void setEType(String EType) {
        this.EType = EType;
    }
}

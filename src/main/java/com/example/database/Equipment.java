package com.example.database;

public class Equipment {
    int EqID,EqCost,SuID;
    String EqName;
    String EqQuality;

    public Equipment(int eqID, String eqName, String eqQuality, int eqCost, int suID) {
        EqID = eqID;
        EqCost = eqCost;
        SuID = suID;
        EqName = eqName;
        EqQuality = eqQuality;
    }

    public int getEqID() {
        return EqID;
    }

    public void setEqID(int eqID) {
        EqID = eqID;
    }

    public int getEqCost() {
        return EqCost;
    }

    public void setEqCost(int eqCost) {
        EqCost = eqCost;
    }

    public int getSuID() {
        return SuID;
    }

    public void setSuID(int suID) {
        SuID = suID;
    }

    public String getEqName() {
        return EqName;
    }

    public void setEqName(String eqName) {
        EqName = eqName;
    }

    public String getEqQuality() {
        return EqQuality;
    }

    public void setEqQuality(String eqQuality) {
        EqQuality = eqQuality;
    }
}

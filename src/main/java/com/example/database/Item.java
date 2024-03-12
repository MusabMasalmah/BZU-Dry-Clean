package com.example.database;
public class Item {

    int IID, ICost, CID;
    String IType;

    public Item(int IID, String IType, int ICost, int CID) {
        this.IID = IID;
        this.IType = IType;
        this.ICost = ICost;
        this.CID = CID;



    }
    public int getIID() {
        return IID;
    }

    public void setIID(int IID) {
        this.IID = IID;
    }

    public int getICost() {
        return ICost;
    }

    public void setICost(int ICost) {
        this.ICost = ICost;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getIType() {
        return IType;
    }

    public void setIType(String IType) {
        this.IType = IType;
    }


}
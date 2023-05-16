package com.example.myapplication;

import com.google.firebase.firestore.DocumentReference;

public class Sales {

    private int sid;
    private int pid;
    private double price;
    private int quantity;
    private String sdate;
    private DocumentReference cid;

    public int getSid() { return sid; }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) { this.pid = pid; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public DocumentReference getCid() {
        return cid;
    }

    public void setCid(DocumentReference cid) {
        this.cid = cid;
    }

}

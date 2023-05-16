package com.example.myapplication;

public class Customer {

    private int cid;
    private String cname;
    private String cemail;

    public Customer() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCustomerEmail() {
        return cemail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.cemail = customerEmail;
    }
}

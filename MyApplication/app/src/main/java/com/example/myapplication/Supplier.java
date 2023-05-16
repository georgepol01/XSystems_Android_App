package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mySupplier")
public class Supplier {

    @PrimaryKey
    @ColumnInfo(name = "supplier_id")
    private int id;

    @ColumnInfo(name = "supplier_name")
    private String name;

    @ColumnInfo(name = "supplier_address")
    private String address;

    @ColumnInfo(name = "supplier_phone")
    private String phone;

    @ColumnInfo(name = "supplier_email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

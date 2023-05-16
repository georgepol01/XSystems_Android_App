package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myProduct")
public class Product {

    @PrimaryKey
    @ColumnInfo(name = "product_id")
    private int id;

    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "product_stock")
    private int stock;

    @ColumnInfo(name = "product_price")
    private double price;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

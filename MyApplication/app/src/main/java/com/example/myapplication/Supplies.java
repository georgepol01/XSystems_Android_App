package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "mySupplies",
        primaryKeys = {"supplies_id", "supplier_id", "product_id"},
        foreignKeys = {
                @ForeignKey(entity = Supplier.class,
                        parentColumns = "supplier_id",
                        childColumns = "supplier_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Product.class,
                        parentColumns = "product_id",
                        childColumns = "product_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})
public class Supplies {

    @ColumnInfo(name = "supplies_id")
    private int id;

    @ColumnInfo(name = "supplier_id")
    private int idSupplier;

    @ColumnInfo(name = "product_id")
    private int idProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}

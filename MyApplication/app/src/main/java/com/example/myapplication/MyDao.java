package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    //Supplies
    @Query("SELECT * FROM mySupplies")
    List<Supplies> getSupplies();

    @Query("SELECT * FROM mySupplies WHERE supplies_id = :suppliesId LIMIT 1")
    Supplies getSuppliesById(int suppliesId);

    @Insert
    void insertSupplies(Supplies supplies);

    @Delete
    void deleteSupplies(Supplies supplies);

    @Query("UPDATE mySupplies SET supplier_id=:newSupplierId, product_id=:newProductId WHERE supplies_id=:suppliesId")
    int updateSupplies(int suppliesId, int newSupplierId, int newProductId);

    @Query("SELECT COUNT(*) FROM mySupplies WHERE supplier_id = :supplierId AND product_id = :productId")
    int checkSuppliesExistence(int supplierId, int productId);


    //Supplier
    @Query("SELECT * FROM mySupplier")
    List<Supplier> getSupplier();

    @Query("SELECT * FROM mySupplier WHERE supplier_id = :supplierId LIMIT 1")
    Supplier getSupplierById(int supplierId);

    @Query("SELECT mySupplier.supplier_id,mySupplier.supplier_name " +
            "FROM mySupplier JOIN mySupplies ON mySupplier.supplier_id = mySupplies.supplier_id " +
            "GROUP BY mySupplies.supplier_id " +
            "ORDER BY COUNT(mySupplies.product_id) DESC " +
            "LIMIT 1;")
    List<Supplier> getQuery7();

    @Insert
    void insertSupplier(Supplier supplier);

    @Delete
    void deleteSupplier(Supplier supplier);

    @Update
    int updateSupplier(Supplier supplier);


    //Product
    @Query("SELECT * FROM myProduct")
    List<Product> getProduct();

    @Query("SELECT * FROM myProduct WHERE product_id = :productId LIMIT 1")
    Product getProductById(int productId);

    @Query("SELECT * FROM myProduct WHERE product_stock < 10")
    List<Product> getQuery6();

    @Query("SELECT myProduct.* " +
            "FROM myProduct JOIN mySupplies ON myProduct.product_id = mySupplies.product_id " +
            "GROUP BY mySupplies.product_id " +
            "HAVING COUNT(DISTINCT mySupplies.supplier_id) > 1;")
    List<Product> getQuery8();

    @Insert
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Update
    int updateProduct(Product product);

}

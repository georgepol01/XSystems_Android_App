package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Supplier.class, Product.class, Supplies.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}

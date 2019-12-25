package com.example.myapplication1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);

    @Query("DELETE FROM product_table")
    void deleteAll();

    //TODO update?
    @Update
    void update(Product product);

    @Query("SELECT * FROM product_table")
    LiveData<List<Product>> getProducts();
}

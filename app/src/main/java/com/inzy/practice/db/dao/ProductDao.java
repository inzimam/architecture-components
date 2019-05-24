package com.inzy.practice.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.inzy.practice.entity.ProductEntitiy;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntitiy>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntitiy> products);

    @Query("select * from products where id = :productId")
    LiveData<ProductEntitiy> loadProduct(int productId);

    @Query("select * from products where id = :productId")
    ProductEntitiy loadProductSync(int productId);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntitiy>> searchAllProducts();
}

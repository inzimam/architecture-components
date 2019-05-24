package com.inzy.practice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.inzy.practice.db.AppDatabase;
import com.inzy.practice.entity.ProductEntitiy;

import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntitiy>> mObservableProducts;

    private DataRepository(AppDatabase mDatabase) {
        this.mDatabase = mDatabase;
        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.addSource(this.mDatabase.productDao().loadAllProducts(), new Observer<List<ProductEntitiy>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntitiy> productEntities) {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableProducts.postValue(productEntities);
                }
            }
        });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductEntitiy>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntitiy> loadProduct(final int productId) {
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<ProductEntitiy>> searchProducts() {
        return mDatabase.productDao().searchAllProducts();
    }
}

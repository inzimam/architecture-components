package com.inzy.practice.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.inzy.practice.BasicApp;
import com.inzy.practice.DataRepository;
import com.inzy.practice.entity.ProductEntitiy;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {

    private final DataRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<ProductEntitiy>> mObservableProducts;


    public ProductListViewModel(@NonNull Application application) {
        super(application);
        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.setValue(null);
        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<ProductEntitiy>> products = mRepository.getProducts();
        mObservableProducts.addSource(products, mObservableProducts::setValue);
    }

    public LiveData<List<ProductEntitiy>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<List<ProductEntitiy>> searchProducts(String query) {
        return mRepository.searchProducts();
    }
    // TODO: Implement the ViewModel
}

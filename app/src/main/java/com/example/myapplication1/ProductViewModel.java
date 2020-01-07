package com.example.myapplication1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication1.Database.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;
    private LiveData<List<Product>> mAllProducts;

    public ProductViewModel(Application application) {
        super(application);
        mRepository= new ProductRepository(application);
        mAllProducts = mRepository.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts(){
        return mAllProducts;
    }

    public void insertProduct(Product product){
        mRepository.insertProduct(product);
    }
    public void updateProduct(Product product){
        mRepository.updateProduct(product);
    }
    public void deleteProduct(Product product){
        mRepository.deleteProduct(product);
    }
}

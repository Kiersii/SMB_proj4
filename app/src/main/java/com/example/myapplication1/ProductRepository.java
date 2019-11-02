package com.example.myapplication1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    ProductRepository(Application application){
        ProductRoomDatabase db= ProductRoomDatabase.getDatabase(application);
        mProductDao=db.productDao();
        mAllProducts = mProductDao.getProducts();
    }
    LiveData<List<Product>> getAllProducts(){
        return mAllProducts;
    }
    public void insert(Product product){
        new insertAsyncTask(mProductDao).execute(product);
    }
    private static class insertAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao mAsyncTaskDao;
        insertAsyncTask(ProductDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.insert(products[0]);
            return null;
        }
    }
}

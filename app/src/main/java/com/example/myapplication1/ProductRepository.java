package com.example.myapplication1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication1.Database.Product;
import com.example.myapplication1.Database.ProductDao;
import com.example.myapplication1.Database.ProductRoomDatabase;

import java.util.List;

public class ProductRepository {

    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    ProductRepository(Application application){
        ProductRoomDatabase db= ProductRoomDatabase.getDatabase(application);
            mProductDao=db.productDao();
        mAllProducts = mProductDao.getProducts();
    }
    public LiveData<List<Product>> getAllProducts(){
        return mAllProducts;
    }

    public void insertProduct(Product product){
        new insertAsyncTask(mProductDao).execute(product);
    }
    public void updateProduct(Product product){
        new updateAsyncTask(mProductDao).execute(product);
    }
    public void deleteProduct(Product product){
        new deleteAsyncTask(mProductDao).execute(product);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao mAsyncTaskDao;
        public insertAsyncTask(ProductDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.insertProduct(products[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao mAsyncTaskDao;

        public updateAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.updateProduct(products[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Product, Void, Void>{

        private ProductDao mAsyncTaskDao;
        public deleteAsyncTask(ProductDao dao){
            mAsyncTaskDao=dao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            mAsyncTaskDao.deleteProduct(products[0]);
            return null;
        }
    }
}

package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    private ProductViewModel mProductViewModel;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list2);

        rv= findViewById(R.id.rv1);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        //new ViewModelProvider(this).get(ProductViewModel.class);

        //final ProductAdapter pa = new ProductAdapter(initProductList(),this);//w tutorialu byla funkcja z jednym argumentem
        final ProductAdapter pa = new ProductAdapter(this);
        initProductList(pa);
        rv.setAdapter(pa);


    }

    public void initProductList(final ProductAdapter pa){
        mProductViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        mProductViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                pa.setProductList(products);
            }
        });
    }
    public void clickAdd(View view){
        Intent intent3= new Intent(this, AddActivity.class);
        //startActivity(intent3);
        startActivityForResult(intent3, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Product product = new Product(data.getStringExtra(AddActivity.EXTRA_REPLY),1,1,false);
            mProductViewModel.insert(product);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "dupa cycki nie działą",
                    Toast.LENGTH_LONG).show();
        }
    }

    /*private List<Product> initProductList(ProductAdapter pa){
        List <Product> il= new ArrayList<Product>();
        il.add(new Product("Mleko", 2,5,false));
        il.add(new Product("Woda", 5,5,false));
        return il;
    }*/
}

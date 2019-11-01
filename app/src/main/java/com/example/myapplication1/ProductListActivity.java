package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list2);
        rv= findViewById(R.id.rv1);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        ProductAdapter pa = new ProductAdapter(initProductList(),this);
        rv.setAdapter(pa);

    }

    private List<Product> initProductList(){
        List <Product> il= new ArrayList<Product>();
        il.add(new Product("Mleko", 2,5,false));
        il.add(new Product("Woda", 5,5,false));
        return il;
    }
}

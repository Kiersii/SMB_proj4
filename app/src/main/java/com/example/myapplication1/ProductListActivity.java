package com.example.myapplication1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication1.Database.Product;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_PRODUCT_ACTIVITY_REQUEST_CODE = 2;
    private ProductViewModel mProductViewModel;
    private RecyclerView rv;
    private ProductAdapter pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list2);
        rv= findViewById(R.id.rv1);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        pa= new ProductAdapter(this);
        initProductList(pa);
        rv.setAdapter(pa);
        setListeners();
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
        intent3.putExtra("requestcode", NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
        startActivityForResult(intent3, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==EDIT_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode== RESULT_OK){
            Product product = data.getParcelableExtra("produkt");
            mProductViewModel.updateProduct(product);

            Toast.makeText(
                    getApplicationContext(),
                    "Produkt został zedytowany.",
                    Toast.LENGTH_LONG).show();
        }else
            if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Product product = data.getParcelableExtra("produkt");
            mProductViewModel.insertProduct(product);

            /*    Toast.makeText(
                        getApplicationContext(),
                        "Produkt został dodany.",
                        Toast.LENGTH_LONG).show();*/
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Nic nie dodano/edytowano.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void setListeners(){
        pa.setOnBoughtClickListener(new ProductAdapter.OnBoughtClickListener() {
            @Override
            public void OnBoughtClickListener(Product product) {
                if(!product.isBought()){
                    product.setBought(true);
                }else{
                    product.setBought(false);
                }
                mProductViewModel.updateProduct(product);
            }
        });

        pa.setOnItemLongClickListener(new ProductAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(final Product product) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this);
                builder.setTitle("Usuwanie produktu");
                builder.setMessage("Czy chcesz usunąć produkt?");
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mProductViewModel.deleteProduct(product);
                    }
                });
                builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        pa.setOnItemClickListener(new ProductAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent3= new Intent(getApplicationContext(),AddActivity.class);
                intent3.putExtra("produkt",product);
                intent3.putExtra("requestcode", EDIT_PRODUCT_ACTIVITY_REQUEST_CODE);
                startActivityForResult(intent3, EDIT_PRODUCT_ACTIVITY_REQUEST_CODE);

            }
        });
    }

    /*private List<Product> initProductList(ProductAdapter pa){
        List <Product> il= new ArrayList<Product>();
        il.add(new Product("Mleko", 2,5,false));
        il.add(new Product("Woda", 5,5,false));
        return il;
    }*/
}

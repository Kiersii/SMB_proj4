package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.myapplication1.Database.Product;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.productlistsql.REPLY";
    public static final String price_reply = "com.example.android.productlistsql.REPLY1";
    public static final String count_reply = "com.example.android.productlistsql.REPLY2";


    private EditText EditName,EditPrice,EditCount;
    private CheckBox bought;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditName=findViewById(R.id.editName);
        EditPrice=findViewById(R.id.editPrice);
        EditCount=findViewById(R.id.editCount);
        bought=findViewById(R.id.checkBox);

        final Intent i = getIntent();
        //i.getIntExtra("requestcode",0);
       // System.out.println( i.getIntExtra("requestcode",0));
        if(i.getIntExtra("requestcode",0)==2){
           // System.out.println("weszło do ifa");
            product = i.getParcelableExtra("produkt");
            EditName.setText(product.getName());
            EditPrice.setText(product.getPrice()+ "");
            EditCount.setText(product.getCount()+"");
            bought.setChecked(product.isBought());
        }

        Button button= findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
               /* if(TextUtils.isEmpty(EditName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }else*/
                    if(i.getIntExtra("requestcode",0)==2){
                   product.setName(EditName.getText().toString());
                   product.setPrice(Integer.parseInt(EditPrice.getText().toString()));
                   product.setCount(Integer.parseInt(EditCount.getText().toString()));
                   product.setBought(bought.isChecked());
                   replyIntent.putExtra("produkt",product);
                   setResult(RESULT_OK,replyIntent);
                } else
                    if(i.getIntExtra("requestcode",0)==1){
                        Product product = new Product(
                                EditName.getText().toString(),
                                Integer.parseInt(EditPrice.getText().toString()),
                                Integer.parseInt(EditCount.getText().toString()),
                                bought.isChecked());
                    replyIntent.putExtra("produkt", product);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });












        /*button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(EditName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String name= EditName.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,name);
                    int price = Integer.parseInt(EditPrice.getText().toString());
                    replyIntent.putExtra(price_reply,price);
                    int count = Integer.parseInt(EditCount.getText().toString());
                    replyIntent.putExtra(count_reply, count);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });*/
    }
}

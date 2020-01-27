package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();
        tv=findViewById(R.id.textView);
        SharedPreferences a = getSharedPreferences("Options1", Context.MODE_PRIVATE);

        int font = a.getInt("size",24);
        int color = a.getInt("color",0xff000000);
        tv.setTextSize(font);
        tv.setTextColor(color);
    }
    public void clickOptions(View view){
        Intent intent1= new Intent(this, OptionsActivity.class);
        startActivity(intent1);

    }
    public void clickList(View view){
        Intent intent2= new Intent(this, ProductListActivity.class);
        startActivity(intent2);

    }

    public void clickMap(View view) {
        Intent intent2= new Intent(this, MapsActivity.class);
        startActivity(intent2);
    }

    public void clickListShop(View view) {
        Intent intent2= new Intent(this, ListActivity.class);
        startActivity(intent2);
    }
}

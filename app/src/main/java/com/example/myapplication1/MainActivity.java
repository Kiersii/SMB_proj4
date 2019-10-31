package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    //SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sp=getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void clickOptions(View view){
        Intent intent1= new Intent(this, OptionsActivity.class);
        startActivity(intent1);

    }
    public void clickList(View view){
        Intent intent2= new Intent(this, ProductListActivity.class);
        startActivity(intent2);

    }
}

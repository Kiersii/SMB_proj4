package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.productlistsql.REPLY";

    private EditText EditName,EditPrice,EditCount;
    private CheckBox bought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditName=findViewById(R.id.editName);
        EditPrice=findViewById(R.id.editPrice);
        EditCount=findViewById(R.id.editCount);
        bought=findViewById(R.id.checkBox);

        Button button= findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(EditName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String name= EditName.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,name);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
    }
}

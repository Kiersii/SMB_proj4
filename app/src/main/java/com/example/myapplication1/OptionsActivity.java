package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    TextView tvFont, tvSize, tvColor;
    private EditText etFont;
    private RadioGroup rg;
    private RadioButton rb;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        tvFont=findViewById(R.id.tv2);
        tvSize =findViewById(R.id.tv3);
        tvColor =findViewById(R.id.tv4);
        etFont = findViewById(R.id.et1);
        rg = findViewById(R.id.radioGroup);
        sp=getSharedPreferences("Options", Context.MODE_PRIVATE);
    }

    public void clickCommit(View view){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("size", etFont.getText().toString());
        int selectedID = rg.getCheckedRadioButtonId();
        rb=findViewById(selectedID);
        editor.putString("color",rb.getText().toString());
        editor.apply();
        //jeszcze zrobic jakiegos defaulta tutaj
      /*  //SharedPreferences a = getPreferences("Options", Context.MODE_PRIVATE);
        String str1 = sp.getString("size","nic");
        String str2 = sp.getString("color","nic2");

        String str = str1 + str2;
        Toast msg = Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG);
        msg.show();*/
    }
}

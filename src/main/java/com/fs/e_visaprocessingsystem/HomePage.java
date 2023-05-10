package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn3,btn4;
    SharedPreferences sharedpreferences1;
    public static final String SHARED_PREFS1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        btn1= findViewById(R.id.button);
        btn3= findViewById(R.id.button3);
        btn4= findViewById(R.id.button4);
        btn1.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
                Toast.makeText(this, "User Page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                startActivity(new Intent(getApplicationContext(), ExecutiveLogin.class));
                //    Toast.makeText(this, "Admin Page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                startActivity(new Intent(getApplicationContext(), AdminLogin.class));
                //    Toast.makeText(this, "Admin Page", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
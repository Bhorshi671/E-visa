package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHome extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn3,btn4,btn5;
    SharedPreferences sharedpreferences1;
    public static final String SHARED_PREFS1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        btn1= findViewById(R.id.button);
        btn3= findViewById(R.id.button3);
        btn1.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:

                startActivity(new Intent(getApplicationContext(), ApplyVisa.class));
                // Toast.makeText(this, "Add Products", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                startActivity(new Intent(getApplicationContext(), RenewalVisa.class));
                // Toast.makeText(this, "View Products", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
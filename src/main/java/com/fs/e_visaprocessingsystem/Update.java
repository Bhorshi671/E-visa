package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

public class Update extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    EditText mobileno,message,message1;
    Button sendsms;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        checkForSmsPermission();
        mobileno=(EditText)findViewById(R.id.editText1);
        message=(EditText)findViewById(R.id.editText2);
        message1=(EditText)findViewById(R.id.sdate);

        sendsms=(Button)findViewById(R.id.button1);
        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear        = c.get(Calendar.YEAR); // current year
                int mMonth       = c.get(Calendar.MONTH); // current month
                int mDay         = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(Update.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                message1.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });


        //Performing action on button click
        sendsms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String no=mobileno.getText().toString();
                String msg=message.getText().toString();
                String msg1=message1.getText().toString();

                System.out.println("Check sms");
                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);

                SmsManager sms=SmsManager.getDefault();
                sms.
                        sendTextMessage(no, null, msg+" Date "+msg1, null,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "permission_not_granted");
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            //enableSmsButton();
        }
    }

}
package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ApplyVisa extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "Upload Image";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    // I am using my local server for uploading image, you should replace it with your server address
    public static final String UPLOAD_URL = "http://"+ServerConnect.serverip+"/Android/evisaprocessing/visaapply.php";

    public static final String UPLOAD_KEY = "upload_image";

    private int PICK_IMAGE_REQUEST = 100;

    private Button btnSelect, btnUpload;
    private TextView txtStatus;

    private ImageView imgView;

    private Bitmap bitmap;

    private Uri filePath;

    private String selectedFilePath;

    EditText Gn,Gt,Gf,Gf4,Gf5,Gf6,Gf7;
    Spinner Sp1;
    String gname,contact,vehiels,Age,Birth,Number,cnumber,cardcv;
    SharedPreferences sharedpreferences1;
    DatePickerDialog datePickerDialog;
    public static final String SHARED_PREFS1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_visa);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        imgView = (ImageView) findViewById(R.id.imgView);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        Sp1=findViewById(R.id.spd);
        btnUpload = (Button) findViewById(R.id.reg);
        txtStatus = (TextView) findViewById(R.id.textView2);
        Gn=findViewById(R.id.name);
        Gt=findViewById(R.id.idno);
        Gf=findViewById(R.id.fname);
        Gf4=findViewById(R.id.dob);
        Gf5=findViewById(R.id.con);
        Gf6=findViewById(R.id.cardnumber);
        Gf7=findViewById(R.id.cvv);
        additemsOnSpinner1();
        btnSelect.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        Gf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear        = c.get(Calendar.YEAR); // current year
                int mMonth       = c.get(Calendar.MONTH); // current month
                int mDay         = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(ApplyVisa.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Gf4.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });



    }
    public void additemsOnSpinner1()
    {

        View v = Sp1.getSelectedView();

        List<String> list= new ArrayList<String>();
        list.add("India");
        list.add("USA");
        list.add("Australia");
        list.add("Switzerland");
        list.add("Germany");
        list.add("Japan");
        list.add("Canada");
        list.add("New Zealand");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        Sp1.setAdapter(dataAdapter);
    }

    Handler handler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "Handler " + msg.what);
            if (msg.what == 1) {
                txtStatus.setText("Upload Success");
                Toast.makeText(getApplicationContext(), "Uploaded Sucessfully", Toast.LENGTH_SHORT).show();

                Gn.setText("");Gt.setText("");imgView.setImageDrawable(null);

            } else
                txtStatus.setText("Upload Error");

        }

    };

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            selectedFilePath = getPath(filePath);
            Log.i(TAG, " File path : " + selectedFilePath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void uploadImage() {
        gname= Gn.getText().toString();

        contact=Gt.getText().toString();
        vehiels=Sp1.getSelectedItem().toString();
        Age=Gf.getText().toString();
        Birth=Gf4.getText().toString();
        Number=Gf5.getText().toString();
        cnumber=Gf6.getText().toString();
        cardcv=Gf7.getText().toString();



        if(gname.equals("")||contact.equals("")||vehiels.equals("")||Age.equals("")||Birth.equals("")||Number.equals("")||cnumber.equals("")
                ||cardcv.equals(""))
        {
            Toast.makeText(this, "Fill All  Details", Toast.LENGTH_SHORT).show();
            txtStatus.setText("Upload Failed");
        }else
        {
            UploadImageApacheHttp uploadTask = new UploadImageApacheHttp();
            uploadTask.doFileUpload(UPLOAD_URL+"?gname="+ URLEncoder.encode(gname)+"&gname1="+URLEncoder.encode(contact)
                    +"&gtype="+URLEncoder.encode(vehiels) +"&Age="+URLEncoder.encode(Age) +
                    "&Birth="+URLEncoder.encode(Birth) +"&Number="+URLEncoder.encode(Number) +
                    "&image="+sharedpreferences1.getString("AID", null), bitmap, handler);
            Toast.makeText(getApplicationContext(), "Uploaded Sucessfully", Toast.LENGTH_SHORT).show();
            Intent lo=new Intent(getApplicationContext(),UserHome.class);
            startActivity(lo);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnSelect)
            showFileChooser();
        else {
            txtStatus.setText("Uploading Started...");
            uploadImage();
        }
    }

}


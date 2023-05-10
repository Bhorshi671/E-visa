package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewRenewalVisa extends AppCompatActivity {
    List<DataAdapter> ListOfdataAdapter;


    RecyclerView recyclerView;
    SharedPreferences sp5;
    String HTTP_JSON_URL = "http://"+ServerConnect.serverip+"/Android/evisaprocessing/viewrenewalvisa.php";

    String Image_Name_JSON = "pid";
    String Image_URL_JSON = "name";
    String Image_Size = "image";
    String Image_price = "name1";
    String birth = "idnumber";
    String Image_Pack = "image1";
    String uid;
    ProgressDialog pDialog;
    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    static int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<String> Price;
    ArrayList<String> Itemid;
    ArrayList<String> Phone;
    static ArrayList<String> FID;


    public static final String SHARED_PREFS1 = "";

    SharedPreferences sharedpreferences1;
    TextView v;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_renewal_visa);
        ImageTitleNameArrayListForClick = new ArrayList<>();
        Price = new ArrayList<>();
        Itemid = new ArrayList<>();
        Phone = new ArrayList<>();
        FID = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);



        JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(ViewRenewalVisa.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    Log.i("Read",FID.get(RecyclerViewItemPosition));
                    // alertDialog(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition),Price.get(RecyclerViewItemPosition),
                    // Itemid.get(RecyclerViewItemPosition),Phone.get(RecyclerViewItemPosition),FID.get(RecyclerViewItemPosition));
                    //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                    // Price.add(json.getString(Image_Price));
                    // Itemid.add(json.getString(GID));

                    // Showing RecyclerView Clicked Item value using Toast.
                    // Toast.makeText(UserHome.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();

                    Intent in = new Intent(ViewRenewalVisa.this, Update.class);
                    startActivity(in);

                    //editor.putString("FID",FID.get(RecyclerViewItemPosition));
                    // editor.commit();
                    // startActivity(in);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }


    Handler handler =  new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //  Log.i(TAG, "Handler " + msg.what);
            if (msg.what == 1) {

                Toast.makeText(getApplicationContext(), "Booked Sucessfully", Toast.LENGTH_SHORT).show();
                v.setVisibility(View.VISIBLE);


            } else
                Toast.makeText(getApplicationContext(), "Booking Error", Toast.LENGTH_SHORT).show();

        }

    };

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(ViewRenewalVisa.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setImageTitle("Name : "+json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageType("Id Number : "+json.getString(birth));
                GetDataAdapter2.setImageSize("Visa Number  : "+json.getString(Image_Pack));

                GetDataAdapter2.setImagePrice("Country  : "+json.getString(Image_price));
                GetDataAdapter2.setImagePack("Contact : "+json.getString(Image_URL_JSON));


                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                Price.add(json.getString(Image_URL_JSON));
                Itemid.add(json.getString(Image_URL_JSON));
                Phone.add(json.getString(Image_Pack));
                FID.add(json.getString(Image_Name_JSON));

                GetDataAdapter2.setImageUrl("http://"+ServerConnect.serverip+"/Android/evisaprocessing/upload/"+json.getString(Image_Size)+".jpg");

            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
}
package com.fs.e_visaprocessingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
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

public class ViewUserDetails extends AppCompatActivity {
    List<DataAdapter> ListOfdataAdapter;

    RecyclerView recyclerView;

    String HTTP_JSON_URL = "http://"+ServerConnect.serverip+"/Android/evisaprocessing/viewuser.php"; //+http://androidblog.esy.es/ImageJsonData.php";

    // public static final String UPLOAD_URL = "http://"+MainActivity.sip+"/Android/agrimarketing/updatecart.php";

    String name = "gname";
    String staffna = "contact";
    String place = "totp";
    String time = "count";
    String date ="address";
    ProgressDialog pDialog;
    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<String> Price;
    ArrayList<String> Itemid;
    public static final String SHARED_PREFS1 = "";

    SharedPreferences sharedpreferences1;
    TextView p2,bv1;
    Integer total=0;
    String billid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_details);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);

        ImageTitleNameArrayListForClick = new ArrayList<>();
        Price = new ArrayList<>();
        Itemid = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview9);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        // v1=findViewById(R.id.tot);
        bv1=findViewById(R.id.bi);
        p2=findViewById(R.id.payb);

        Log.i("TAG", HTTP_JSON_URL);

        JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(ViewUserDetails.this, new GestureDetector.SimpleOnGestureListener() {

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

                    //  alertDialog(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition),Price.get(RecyclerViewItemPosition),Itemid.get(RecyclerViewItemPosition));

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
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //  Log.i(TAG, "Handler " + msg.what);
            if (msg.what == 1) {

                Toast.makeText(getApplicationContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());


            } else
                Toast.makeText(getApplicationContext(), "Upload Error", Toast.LENGTH_SHORT).show();

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

        requestQueue = Volley.newRequestQueue(ViewUserDetails.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {
                Log.i("TAG", HTTP_JSON_URL);
                json = array.getJSONObject(i);


                GetDataAdapter2.setImageTitle(" name : "+json.getString(name));

                GetDataAdapter2.setImagePack("Gender : "+json.getString(place));
                GetDataAdapter2.setImageType("Contact : "+json.getString(staffna));
                GetDataAdapter2.setImagePrice("Date : "+json.getString(date));
                GetDataAdapter2.setImagename("Address : "+ json.getString(time));


                // Adding image title name in array to display on RecyclerView click event.
                //billid= json.getString(Image_URL_JSON);





            } catch (JSONException e) {

                e.printStackTrace();
            }

            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerBillAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
        //v1.setText(""+total);
        bv1.setText(billid);
    }






}
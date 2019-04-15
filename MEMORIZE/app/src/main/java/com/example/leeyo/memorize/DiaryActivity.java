package com.example.leeyo.memorize;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class DiaryActivity extends AppCompatActivity {

    TextView diaryTitle;
    TextView diaryDate;
    TextView diaryPlace;
    TextView diaryContents;
    String diaryNo;
    Bitmap bmImg;
    String imgCount;
    ImageView Img_one;
    ImageView Img_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        Intent intent = getIntent();
        diaryNo = intent.getExtras().getString("diaryNo");

        diaryTitle = findViewById(R.id.diaryTitle);
        diaryDate = findViewById(R.id.diaryDate);
        diaryPlace = findViewById(R.id.diaryPlace);
        diaryContents = findViewById(R.id.diaryContents);
        Img_one = findViewById(R.id.img_one);
        Img_two = findViewById(R.id.img_two);

        loadData();
    }

    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://skdus1995.cafe24.com/diary_contents.php?diaryNo=" + diaryNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        diaryTitle.setText(object.getString("diaryTitle"));
                        diaryDate.setText(object.getString("diaryDate"));
                        diaryPlace.setText(object.getString("diaryPlace")+"에서");
                        diaryContents.setText(object.getString("diaryContents"));
                        String one = object.getString("diaryImg_one");
                        String two = object.getString("diaryImg_two");
                        ShopImg1 ShopImgtask1 = new ShopImg1();
                        ShopImgtask1.execute("http://skdus1995.cafe24.com/image/" + one);
                        //ShopImg2 ShopImgtask2 = new ShopImg2();
                        //ShopImgtask2.execute("http://skdus1995.cafe24.com/image/" + two);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DiaryActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(DiaryActivity.this);
        requestQueue.add(stringRequest);
    }

    class ShopImg1 extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            }catch(IOException e){
                e.printStackTrace();
            }
            return bmImg;
        }
        protected void onPostExecute(Bitmap img){
                Img_one.setImageBitmap(bmImg);
        }
    }

    class ShopImg2 extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            }catch(IOException e){
                e.printStackTrace();
            }
            return bmImg;
        }
        protected void onPostExecute(Bitmap img){
            Img_two.setImageBitmap(bmImg);
        }
    }
}

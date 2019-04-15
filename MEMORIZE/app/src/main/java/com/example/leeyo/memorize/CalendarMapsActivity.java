package com.example.leeyo.memorize;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;

public class CalendarMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String[] diaryDate;
    String[] diaryPlace;
    Double[] latitude;
    Double[] longitude;
    String result = "fail";
    SupportMapFragment mapFragment;
    JSONArray array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_maps);
        dateloadData();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
    }

    private void dateloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://skdus1995.cafe24.com/calendar_map.php?userNo=" + MainActivity.userNo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        diaryPlace = new String[array.length()];
                        latitude = new Double[array.length()];
                        longitude = new Double[array.length()];
                        String place = obj.getString("diaryPlace").toString();
                        Double lat = obj.getDouble("latitude");
                        Double lng = obj.getDouble("longitude");
                        diaryPlace[i] = place;
                        latitude[i] = lat;
                        longitude[i] = lng;
                        Log.v(i+"array",diaryPlace[i] + "/" + latitude[i] + "/" + longitude[i]);
                    }
                    result = "success";
                    mapFragment.getMapAsync(CalendarMapsActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CalendarMapsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CalendarMapsActivity.this);
        requestQueue.add(stringRequest);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        Double Dlatitude = null;
        Double Dlongitude = null;
        Log.v("ssssss", "sffffffssss");
        if (result.equals("success")) {
            Log.v("vvvvvv", "sssss");
            for (int i = 0; i < array.length(); i++) {
                MarkerOptions mOptions = new MarkerOptions();
/*                Log.v("aa", Dlatitude+"");
                Log.v("pppppp", latitude[i]+"");
                Dlatitude = latitude[i]; // 위도
                Log.v("bb", Dlatitude+"");
                Dlongitude = longitude[i]; // 경도*/


                Dlatitude = 36.9697366; // 위도
                Log.v("bb", Dlatitude+"");
                Dlongitude = 127.8713231; // 경도

                mOptions.position(new LatLng(Dlatitude + i, Dlongitude))
                        .title(diaryPlace[i]);
                mMap.addMarker(mOptions);
                Log.v("ewrq", Dlatitude + "/" + Dlongitude + "/" + diaryPlace[i]);
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Dlatitude, Dlongitude)));
        }
    }
}

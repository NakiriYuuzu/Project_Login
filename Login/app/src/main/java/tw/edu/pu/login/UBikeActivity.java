package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class UBikeActivity extends AppCompatActivity {

    String API_KEY = "https://datacenter.taichung.gov.tw/swagger/OpenData/34c2aa94-7924-40cc-96aa-b8d090f0ab69";
    ListView lv;
    MaterialButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubike);

        lv = findViewById(R.id.lv_UBike);
        btnReturn = findViewById(R.id.btn_UBike_Return);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getBikeData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void getBikeData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject allData = new JSONObject(response);
                    int status = allData.getInt("retCode");
                    if (status == 1) {
                        JSONObject allSites = allData.getJSONObject("retVal");
                        Iterator<String> it = allSites.keys();

                        ArrayList<String> mySite = new ArrayList<>();
                        ArrayList<String> myBike = new ArrayList<>();

                        while (it.hasNext()) {
                            String id = it.next();
                            JSONObject siteData = allSites.getJSONObject(id);

                            String areaNames = siteData.getString("sarea");
                            String siteNames = siteData.getString("sna");
                            String loadNames = siteData.getString("ar");
                            int liveBikes = Integer.parseInt(siteData.getString("sbi"));

                            mySite.add(areaNames + " : " + siteNames);

                            if (areaNames.equals("沙鹿區")) {
                                mySite.add(siteNames + " : " + loadNames);
                                myBike.add(String.valueOf(liveBikes));
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    UBikeActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    mySite
                            );
                            lv.setAdapter(adapter);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UBikeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(UBikeActivity.this);
        requestQueue.add(stringRequest);
    }
}
package com.example.framework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.framework.model.DataWeb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataWeb> datawebs= new ArrayList();
    JSONObject jsonObject;
    ListView listview;
    FloatingActionButton btnRefresh;
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.loading);
        getDataWeb();
    }

    void setupListviewWeb () {
        listview = findViewById(R.id.list);
        com.example.framework.AdapterWeb adapter = new com.example.framework.AdapterWeb(this, datawebs);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataWeb web = datawebs.get(position);
            Intent intent = new Intent(MainActivity.this, com.example.framework.DetailWeb.class);
            intent.putExtra("Web", web);
            startActivity(intent);
        }
    };
    final String url = "https://ewinsutriandi.github.io/mockapi/web_framework.json";
    void getDataWeb() {
        datawebs.clear();
        loadingIndicator.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonObject = response;
                        refreshView();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Not Found", error.toString());
                        loadingIndicator.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Pastikan Perangkat Anda Terhubung Dengan Interet", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        setupListviewWeb();
    }

    void refreshView() {
        Iterator<String> key = jsonObject.keys();
        while(key.hasNext()) {
            String nameWeb = key.next();
            try {
                JSONObject data = jsonObject.getJSONObject(nameWeb);
                String description = data.getString("description");
                String original_author = data.getString("original_author");
                String logo = data.getString("logo");
                String official_website = data.getString("official_website");
                datawebs.add(new DataWeb(nameWeb,  official_website,original_author, description, logo));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        loadingIndicator.setVisibility(View.GONE);
        setupListviewWeb();
    }

}
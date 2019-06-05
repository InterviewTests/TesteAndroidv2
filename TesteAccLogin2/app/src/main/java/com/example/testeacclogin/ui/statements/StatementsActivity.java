package com.example.testeacclogin.ui.statements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testeacclogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StatementsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StateAdapter mStateAdapter;
    private ArrayList<StateItem> mStateList;
    private RequestQueue mRequestQueue;

    private ImageButton logoutButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);

        mRecyclerView = findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        logoutButton = findViewById(R.id.btn_logout);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRecyclerView.setAdapter(mStateAdapter);


        mStateList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);


        parseJson();


    }

    private void parseJson() {

        new Thread() {

            @Override
            public void run() {

                String url = "https://bank-app-test.herokuapp.com/api/statements/1";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("statementList");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject stat = jsonArray.getJSONObject(i);

                                        String estTitle = stat.getString("title");
                                        String estDesc = stat.getString("desc");
                                        String estData = stat.getString("date");
                                        double estValue = stat.getDouble("value");



                                        mStateList.add(new StateItem(estTitle, estDesc,
                                                estData,
                                                NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(estValue)));


                                    }


                                    mStateAdapter = new StateAdapter(StatementsActivity.this, mStateList);
                                    mRecyclerView.setAdapter(mStateAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });



                mRequestQueue.add(request);

            }



        }.start();


        logoutButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }

        });


    }

}




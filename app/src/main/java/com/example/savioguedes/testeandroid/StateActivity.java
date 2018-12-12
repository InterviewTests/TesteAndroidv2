package com.example.savioguedes.testeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.savioguedes.testeandroid.adapter.StateAdapter;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {

    private RecyclerView stateRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;


    private List<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        stateRecyclerView = findViewById(R.id.state_recyclerview);
        listItems = new ArrayList<>();

        //config recyclerview
        stateRecyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        stateRecyclerView.setLayoutManager(LayoutManager);

        listItems.add("");
        listItems.add("");
        listItems.add("");
        listItems.add("");
        listItems.add("");
        listItems.add("");

        //set adapter
        adapter = new StateAdapter(listItems, this);
        stateRecyclerView.setAdapter(adapter);
    }
}

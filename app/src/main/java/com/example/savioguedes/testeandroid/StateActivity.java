package com.example.savioguedes.testeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.savioguedes.testeandroid.adapter.StateAdapter;
import com.example.savioguedes.testeandroid.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {

    private RecyclerView stateRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;


    private List<Statement> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        stateRecyclerView = findViewById(R.id.state_recyclerview);
        listItems = new ArrayList<>();

        //config recyclerview
        LayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stateRecyclerView.setLayoutManager(LayoutManager);

        listItems.add(new Statement());
        listItems.add(new Statement());
        listItems.add(new Statement());

        //set adapter
        adapter = new StateAdapter(listItems, this);
        stateRecyclerView.setAdapter(adapter);
    }
}

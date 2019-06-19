package com.example.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bankapp.ui.adapter.AdapterCurrency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity {
    RecyclerView recyclerViewCurrency;
    List<UserDataAccount> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        recyclerViewCurrency = findViewById(R.id.recyclerViewCurrency);
        loadlist();
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCurrency.setLayoutManager(layoutManager);
        recyclerViewCurrency.setHasFixedSize(true);
        AdapterCurrency adapterTimeline = new AdapterCurrency(list);
        recyclerViewCurrency.setAdapter(adapterTimeline);
    }

    private void loadlist(){
        list.add(new UserDataAccount("12/12/2018","Luz",50000));
        list.add(new UserDataAccount("12/12/2018","Drogas",7000));
        list.add(new UserDataAccount("12/12/2018","Jogos",9700));
        list.add(new UserDataAccount("12/12/2018","Água",400));
        list.add(new UserDataAccount("12/12/2018","Gás",200));
        configAdapter();
    }
}

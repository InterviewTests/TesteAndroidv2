package com.example.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bankapp.model.dashboard.statementList;
import com.example.bankapp.ui.DashboardPresenter;
import com.example.bankapp.ui.DashboardViewPresenter;
import com.example.bankapp.ui.adapter.AdapterCurrency;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardViewPresenter.dashboardView {
    RecyclerView recyclerViewCurrency;
    List<UserDataAccount> list = new ArrayList<>();
    List<statementList> listDash;
    DashboardViewPresenter.dashboardPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        presenter = new DashboardPresenter(this);
        recyclerViewCurrency = findViewById(R.id.recyclerViewCurrency);
        //loadlist();

        presenter.getList(1);
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCurrency.setLayoutManager(layoutManager);
        recyclerViewCurrency.setHasFixedSize(true);
        AdapterCurrency adapterTimeline = new AdapterCurrency(listDash);
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

    @Override
    public void showList(List<statementList> list) {
        listDash = list;
        configAdapter();
    }
}

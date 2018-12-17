package com.example.savioguedes.testeandroid.state;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.savioguedes.testeandroid.R;
import com.example.savioguedes.testeandroid.adapter.StateAdapter;
import com.example.savioguedes.testeandroid.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity implements StateContract.View {

    private RecyclerView stateRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LayoutManager;

    private List<Statement> listItems;
    StatePresenter statePresenter;
    Bundle extras;

    private TextView username, account, sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        stateRecyclerView = findViewById(R.id.state_recyclerview);
        listItems = new ArrayList<>();

        statePresenter = new StatePresenter(this, getParent());

        //config recyclerview
        LayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stateRecyclerView.setLayoutManager(LayoutManager);
    }

    @Override
    public void initView() {

        extras = getIntent().getExtras();

        username = findViewById(R.id.state_text_user);
        account = findViewById(R.id.state_text_account);
        sale = findViewById(R.id.state_text_sale);

        username.setText(extras.getString("NOME"));
        account.setText(extras.getString("CONTA"));
        sale.setText(extras.getString("SALDO"));

        fillList();
    }

    @Override
    public void fillList() {

        listItems.add(new Statement());
        listItems.add(new Statement());
        listItems.add(new Statement());

        //set adapter
        adapter = new StateAdapter(listItems, this);
        stateRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void onErroRequest() {

    }
}

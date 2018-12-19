package com.example.jcsantos.santanderteste.Modules.Statements;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.jcsantos.santanderteste.Components.Objects.User;
import com.example.jcsantos.santanderteste.R;

import java.util.ArrayList;

public class StatementActivity extends AppCompatActivity implements StatementResponse {
    TextView name;
    TextView account;
    TextView money;
    RecyclerView recyclerView;
    User userAccount;
    private StatementModel model;
    private ArrayList load = new ArrayList<>();
    private StatementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        name = findViewById(R.id.txt_user_name);
        account = findViewById(R.id.txt_account_number);
        money = findViewById(R.id.txt_money);
        userAccount = new User();
        model = new StatementModel(this, this);

        setup();
    }

    public void setup() {
        userAccount = (User) getIntent().getExtras().getSerializable("user_info");
        name.setText(userAccount.getName());
        account.setText(String.format("%s / %s", userAccount.getBankAccount(), userAccount.getAgency()));
        money.setText(userAccount.getBalance());
        model.requestStatement(userAccount.getUserId());
    }

    public void loadData(){
        Context context = getApplicationContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.statement_recycler);
        adapter = new StatementAdapter(StatementActivity.this, load);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void requestSuccess(ArrayList list) {
        load = list;
        loadData();
    }
}

package com.example.projectbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectbank.Controller.StatementController;
import com.example.projectbank.Model.Statement;
import com.example.projectbank.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtBank;
    private TextView txtAgency;
    private TextView txtBalance;
    private ImageView imgLogout;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);

        initViews();
    }

    public void initViews(){
        txtName = (TextView) findViewById(R.id.edtName);
        txtBank = (TextView) findViewById(R.id.edtBankAccount);
        txtAgency = (TextView) findViewById(R.id.edtAgency);
        txtBalance = (TextView) findViewById(R.id.edtSaldo);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        imgLogout = (ImageView) findViewById(R.id.imgLogout);

        recyclerView.setHasFixedSize(true);
        String cpfOrEmail = getIntent().getStringExtra("cpfOrEmail");
        String password = getIntent().getStringExtra("password");

        new StatementController()
                .getStatmentsService()
                .buscarStatements(1)
                .enqueue(new Callback<Statement>() {
                    @Override
                    public void onResponse(Call<Statement> call, Response<Statement> response) {
                        Statement statement = response.body();
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(statement, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(StatementsActivity.this));
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<Statement> call, Throwable t) {
                        Log.e("StatementService   ", "Erro ao buscar o statements:" + t.getMessage());

                    }
                });

        User user = getIntent().getParcelableExtra("user");
        txtName.setText(user.getUserAccount().getName());
        txtBank.setText(user.getUserAccount().getBankAccount());
        txtAgency.setText(user.getUserAccount().getAgency());
        txtBalance.setText(String.valueOf(user.getUserAccount().getBalance()));
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatementsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.dev.appbank.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.appbank.R;
import com.dev.appbank.adapter.AdapterRecyclerViewStatement;
import com.dev.appbank.model.Login;
import com.dev.appbank.model.Statement;
import com.dev.appbank.model.StatementList;
import com.dev.appbank.model.User;
import com.dev.appbank.model.UserAccount;
import com.dev.appbank.util.Common;
import com.dev.appbank.util.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Statement> listStatements = new ArrayList<>();

    private TextView nameDetailAccount, agencyDetailAccount, balanceDetailAccount;
    private ImageView logoutBtnAccount;
    private ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.bankStatementRecyclerView);
        nameDetailAccount = findViewById(R.id.nameDetailAccount);
        agencyDetailAccount = findViewById(R.id.agencyDetailAccount);
        balanceDetailAccount = findViewById(R.id.balanceDetailAccount);
        logoutBtnAccount = findViewById(R.id.logoutBtnAccount);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.VISIBLE);

        UserAccount userAccount = (UserAccount) getIntent().getSerializableExtra("userAccount");

        if(userAccount != null){
            nameDetailAccount.setText(userAccount.getName());
            agencyDetailAccount.setText(userAccount.getAgency());
            balanceDetailAccount.setText(Common.NUMBER_FORMAT.format(userAccount.getBalance()));

            Call<StatementList> call = new RetrofitConfig(Common.URL).getServiceRetrofit().getStatetments();
            call.enqueue(new Callback<StatementList>() {
                @Override
                public void onResponse(Call<StatementList> call, Response<StatementList> response) {
                    StatementList statementList = response.body();
                    listStatements = statementList.getStatementList();
                    AdapterRecyclerViewStatement adapterTeste = new AdapterRecyclerViewStatement(listStatements);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterTeste);
                    progressBar3.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<StatementList> call, Throwable t) {
                    progressBar3.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Ocorreu um comportamento inesperado, tente novamente!" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(HomeActivity.this, "Ocorreu um comportamento inesperado, tente novamente!", Toast.LENGTH_LONG).show();
        }

        logoutBtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

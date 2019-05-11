package com.example.santanderapp.santander;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santanderapp.santander.adapter.StatementAdapter;
import com.example.santanderapp.santander.interfaceService.StatementsService;
import com.example.santanderapp.santander.model.RequestStatement;
import com.example.santanderapp.santander.model.ResponseStatement;
import com.example.santanderapp.santander.util.Utils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private Integer userId;

    private RecyclerView listExpenses;

    private ImageView ivLogout;
    private TextView tvUser;
    private TextView tvAccount;
    private TextView tvBalance;

    private RecyclerView.LayoutManager layoutRV;
    private StatementAdapter statementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        configureUI();

        getSharedPreferences();

        ivLogout.setOnClickListener(listenerLogout);

        callAPI();
    }

    private void callAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StatementsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StatementsService service = retrofit.create(StatementsService.class);

        RequestStatement requestStatement = new RequestStatement();
        requestStatement.user = userId.toString();

        Call<ResponseStatement> requestCatalog = service.listStat(requestStatement.user);

        requestCatalog.enqueue(new Callback<ResponseStatement>() {
            @Override
            public void onResponse(Call<ResponseStatement> call, Response<ResponseStatement> response) {
                if (!response.isSuccess()) {
                    Toast.makeText(DetailsActivity.this, getString(R.string.error) + response.code(), Toast.LENGTH_SHORT).show();

                } else {
                    if (response.body() != null) {
                        ResponseStatement responseStatement = response.body();

                        layoutRV = new LinearLayoutManager(DetailsActivity.this);
                        statementAdapter = new StatementAdapter(responseStatement.statementList);
                        listExpenses.setAdapter(statementAdapter);
                        listExpenses.setLayoutManager(layoutRV);
                        statementAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseStatement> call, Throwable t) {
                if (!Utils.isConected(DetailsActivity.this)) {
                    Toast.makeText(DetailsActivity.this, getString(R.string.notConnectInternet), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(DetailsActivity.this, getString(R.string.fail) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void configureUI() {

        ivLogout = findViewById(R.id.ivLogout);
        tvUser = findViewById(R.id.tvUser);
        tvAccount = findViewById(R.id.tvAccount);
        tvBalance = findViewById(R.id.tvBalance);
        listExpenses = findViewById(R.id.listExpenses);

    }

    private void getSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.userAccount), MODE_PRIVATE);

        userId = preferences.getInt(getString(R.string.userId), 0);
        tvUser.setText(preferences.getString(getString(R.string.name), ""));
        tvAccount.setText(preferences.getString(getString(R.string.bankAccount), "") + " / " + Utils.formatAccount(preferences.getString(getString(R.string.agency), "")));
        tvBalance.setText(Utils.formatReal(String.valueOf(preferences.getFloat(getString(R.string.balance), 0.0f))));

    }

    //Evento de onclick do botão logout
    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

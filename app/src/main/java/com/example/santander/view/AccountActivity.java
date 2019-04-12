package com.example.santander.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santander.R;
import com.example.santander.model.statementListVO;
import com.example.santander.model.statementVO;
import com.example.santander.model.userAccountVO;
import com.example.santander.viewmodel.API;
import com.example.santander.viewmodel.StatementAdapter;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class AccountActivity extends AppCompatActivity {

    private userAccountVO userAccountVO;
    private statementListVO statementListVO;
    private List<statementVO> statementList;
    private TextView userName;
    private TextView bankAccount;
    private TextView balance;
    private Integer userId;
    private RecyclerView rvStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initVariables();
        initValues();
        buildStatementJSON();
    }

    private void buildStatementJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<statementListVO> call = api.getStatementList(userId);
        call.enqueue(new Callback<statementListVO>() {
            @Override
            public void onResponse(@NonNull Call<statementListVO> call, @NonNull Response<statementListVO> response) {
                statementListVO = response.body();
                if (statementListVO != null) {
                    statementList = statementListVO.getStatementList();
                    StatementAdapter adapter = new StatementAdapter(statementList);
                    rvStatement.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<com.example.santander.model.statementListVO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initVariables() {
        String EXTRA_USER_ACCOUNT = "EXTRA_USER_ACCOUNT";
        userAccountVO = (userAccountVO) getIntent().getSerializableExtra(EXTRA_USER_ACCOUNT);

        userName = findViewById(R.id.tv_account_user_name);
        bankAccount = findViewById(R.id.tv_account_bank_account_number);
        balance = findViewById(R.id.tv_account_balance_value);

        ImageButton imageButton = findViewById(R.id.ib_account_logout);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvStatement = findViewById(R.id.rv_account_statement);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvStatement.setLayoutManager(mLayoutManager);
        rvStatement.setHasFixedSize(true);
    }

    private void initValues() {
        userId = userAccountVO.getUserId();
        userName.setText(userAccountVO.getName());
        String formattedAccount = String.format("%s / %s", userAccountVO.getBankAccount(), userAccountVO.getAgency());
        bankAccount.setText(formattedAccount);
        String formattedBalance = String.format(Locale.getDefault(), "R$ %s,00", userAccountVO.getBalance());
        balance.setText(formattedBalance);
    }
}

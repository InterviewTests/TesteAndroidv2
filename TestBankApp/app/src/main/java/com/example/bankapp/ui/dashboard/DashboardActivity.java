package com.example.bankapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankapp.R;
import com.example.bankapp.helper.MyConveter;
import com.example.bankapp.model.dashboard.StatementList;
import com.example.bankapp.model.user.UserAccount;
import com.example.bankapp.ui.adapter.AdapterCurrency;
import com.example.bankapp.ui.login.LoginActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardViewPresenter.dashboardView {
    RecyclerView recyclerViewCurrency;
    List<StatementList> listDash;

    private TextView textViewNameUser;
    private TextView textViewNumberAccount;
    private TextView textViewCash;
    private ImageView imageViewLogout;
    private DashboardViewPresenter.dashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        UserAccount userData = (UserAccount) intent.getSerializableExtra("userData");

        loadUi();

        actionLogoutButton(userData);


    }

    private void actionLogoutButton(UserAccount userData) {
        setUserDataText(userData);
        imageViewLogout.setOnClickListener(v -> {
            Intent loginIntent = new Intent(DashboardActivity.this,
                    LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });
    }

    private void setUserDataText(UserAccount userData) {
        textViewNameUser.setText(userData.getName());
        textViewCash.setText(MyConveter.formatCurrency(userData.getBalance()));
        textViewNumberAccount.setText(MyConveter.getFormatedInfoBank(userData.getBankAccount(),
                userData.getAgency()));

        presenter.getList(userData.getUserId());
    }

    private void loadUi() {
        presenter = new DashboardPresenter(this);
        recyclerViewCurrency = findViewById(R.id.recyclerViewCurrency);
        imageViewLogout = findViewById(R.id.imageViewLogout);
        textViewNameUser = findViewById(R.id.textViewNameUser);
        textViewCash = findViewById(R.id.textViewCash);
        textViewNumberAccount = findViewById(R.id.textViewNumberAccount);
    }

    private void configAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCurrency.setLayoutManager(layoutManager);
        recyclerViewCurrency.setHasFixedSize(true);
        AdapterCurrency adapterTimeline = new AdapterCurrency(listDash);
        recyclerViewCurrency.setAdapter(adapterTimeline);
    }


    @Override
    public void showList(List<StatementList> list) {
        listDash = list;
        configAdapter();
    }

    @Override
    public void showMessageError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

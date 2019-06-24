package com.example.bankapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankapp.R;
import com.example.bankapp.data.remote.model.dashboard.StatementList;
import com.example.bankapp.data.remote.model.user.UserAccount;
import com.example.bankapp.helper.MyConveter;
import com.example.bankapp.ui.dashboard.adapter.AdapterCurrency;
import com.example.bankapp.ui.login.LoginActivity;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements DashboardViewPresenter.dashboardView {

    private RecyclerView recyclerViewCurrency;
    private List<StatementList> listDash;

    private TextView textViewNameUser;
    private TextView textViewNumberAccount;
    private TextView textViewCash;
    private TextView textViewError;
    private ImageView imageViewLogout;
    private DashboardViewPresenter.dashboardPresenter presenter;
    private UserAccount userData;
    private ProgressBar progressBarDashboard;
    private SwipeRefreshLayout swipeRefreshDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userData = (UserAccount) getIntent().getSerializableExtra("userData");
        loadUi();
        actionLogoutButton();
        setUserDataText();

        swipeRefreshDash.setOnRefreshListener(() -> presenter.getList(userData.getUserId()));
    }

    private void actionLogoutButton() {
        imageViewLogout.setOnClickListener(v ->
                alertDialog()
        );
    }

    private void setUserDataText() {
        textViewNameUser.setText(userData.getName());
        textViewCash.setText(MyConveter.formatCurrency(userData.getBalance()));
        textViewNumberAccount.setText(MyConveter.getFormatedInfoBank(userData.getBankAccount(),
                userData.getAgency()));

        presenter.getList(userData.getUserId());
    }

    private void loadUi() {
        presenter = new DashboardPresenter(this);
        recyclerViewCurrency = findViewById(R.id.recyclerViewCurrency);
        swipeRefreshDash = findViewById(R.id.swipeRefreshDash);
        imageViewLogout = findViewById(R.id.imageViewLogout);
        textViewNameUser = findViewById(R.id.textViewNameUser);
        textViewCash = findViewById(R.id.textViewCash);
        progressBarDashboard = findViewById(R.id.progressBarDashbar);
        textViewError = findViewById(R.id.textViewError);
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
        if (list != null) {
            textViewError.setVisibility(View.GONE);
            listDash = list;
            configAdapter();
        } else {
            textViewError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress(boolean key) {
        progressBarDashboard.setVisibility(key ? View.VISIBLE : View.INVISIBLE);
        textViewError.setVisibility(View.INVISIBLE);
        recyclerViewCurrency.setVisibility(key ? View.INVISIBLE : View.VISIBLE);
        swipeRefreshDash.setRefreshing(false);
    }

    @Override
    public void showMessageError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void alertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Sair")
                .setMessage("Tem certeza que deseja sair da sua conta?")
                .setPositiveButton("Sim",
                        (dialogInterface, i) -> {
                            Intent loginIntent = new Intent(DashboardActivity.this,
                                    LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        })
                .setNegativeButton("Não", (dialog, which) ->
                        dialog.dismiss())
                .show();
    }
}

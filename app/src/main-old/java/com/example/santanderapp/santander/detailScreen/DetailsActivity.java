package com.example.santanderapp.santander.detailScreen;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.santanderapp.santander.R;
import com.example.santanderapp.santander.detailScreen.adapter.StatementAdapter;
import com.example.santanderapp.santander.detailScreen.controller.DetailsController;
import com.example.santanderapp.santander.detailScreen.model.ResponseStatement;
import com.example.santanderapp.santander.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailsActivity extends AppCompatActivity {

    private Integer userId;

    private RecyclerView listExpenses;

    private ImageView ivLogout;
    private TextView tvUser;
    private TextView tvAccount;
    private TextView tvBalance;

    private RecyclerView.LayoutManager layoutRV;
    private StatementAdapter statementAdapter;

    private ProgressDialog progress;

    private DetailsController detailsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        configureUI();

        getSharedPreferences();

        ivLogout.setOnClickListener(listenerLogout);

        detailsController = new DetailsController(this);

        detailsController.callAPI(userId.toString());

    }

    private void configureUI() {

        ivLogout = findViewById(R.id.ivLogout);
        tvUser = findViewById(R.id.tvUser);
        tvAccount = findViewById(R.id.tvAccount);
        tvBalance = findViewById(R.id.tvBalance);
        listExpenses = findViewById(R.id.listExpenses);

        progress = new ProgressDialog(DetailsActivity.this);
        progress.setTitle(getString(R.string.loading));
        progress.setMessage(getString(R.string.whaitDetails));
        progress.setCancelable(false);
        progress.show();


    }


    private void getSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.userAccount), MODE_PRIVATE);

        userId = preferences.getInt(getString(R.string.userId), 0);
        tvUser.setText(preferences.getString(getString(R.string.name), ""));
        tvAccount.setText(preferences.getString(getString(R.string.bankAccount), "") + " / " + Utils.formatAccount(preferences.getString(getString(R.string.agency), "")));
        tvBalance.setText(Utils.formatRealHeader(String.valueOf(preferences.getFloat(getString(R.string.balance), 0.0f))));

    }

    //Evento de onclick do botão logout
    private View.OnClickListener listenerLogout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dismissProgress(ResponseStatement responseStatement) {
        populationScreen(responseStatement);
    }

    public void populationScreen(ResponseStatement responseStatement) {
        progress.dismiss();
        layoutRV = new LinearLayoutManager(DetailsActivity.this);
        statementAdapter = new StatementAdapter(responseStatement.statementList);
        listExpenses.setAdapter(statementAdapter);
        listExpenses.setLayoutManager(layoutRV);
        statementAdapter.notifyDataSetChanged();
    }

    public void register() {
        EventBus.getDefault().register(this);
    }

    public void Unregister() {
        EventBus.getDefault().unregister(this);
    }

}

package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.web.glix.interviewgiovanipaleologo.ActivityBaseCommon;
import com.br.web.glix.interviewgiovanipaleologo.R;
import com.br.web.glix.interviewgiovanipaleologo.adapters.StatementAdapter;
import com.br.web.glix.interviewgiovanipaleologo.models.Statement;
import com.br.web.glix.interviewgiovanipaleologo.models.UserAccount;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.formatarNumeroConta;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.getSoftButtonsBarSizePort;


interface HomeScreenActivityInput {
    void showStatementList(HomeScreenResponse homeScreenResponse);
    void showStatementList(List<Statement> statementList);
}


public class HomeScreenActivity extends ActivityBaseCommon implements HomeScreenActivityInput {
    protected HomeScreenInteractor homeScreenInteractor;

    JSONObject jSonUserAccountLogin;
    UserAccount userAccountLogin;

    ImageView ivHomeLogout;
    TextView tvNomeTitular;
    TextView tvNumeroConta;
    TextView tvSaldoValor;

    RecyclerView rcStatement;
    StatementAdapter statementAdapter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homescreen);

        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ConstraintLayout constraintLayout = findViewById(R.id.clAccountDetails);
        constraintLayout.setPadding(0,0,0, getSoftButtonsBarSizePort(this));

        HomeScreenConfigurator.INSTANCE.configure(this);
        displayScreenData();
    }

    private void displayScreenData() {
        if (getIntent().getStringExtra("jSonUserAccountLogin") != null) {
            try {
                jSonUserAccountLogin = new JSONObject(Objects.requireNonNull(getIntent().getStringExtra("jSonUserAccountLogin")));
                userAccountLogin = new Gson().fromJson(String.valueOf(jSonUserAccountLogin), UserAccount.class);
            } catch(JSONException e){
                e.printStackTrace();
            }
        } else {
            finish();
        }

        String agencyAccount = userAccountLogin.getBankAccount() + " / " + formatarNumeroConta(userAccountLogin.getAgency());

        tvNomeTitular = findViewById(R.id.tvHomeNomeTitular);
        tvNumeroConta = findViewById(R.id.tvHomeNumeroConta);
        tvSaldoValor = findViewById(R.id.tvHomeSaldoValor);

        tvNomeTitular.setText(userAccountLogin.getName());
        tvNumeroConta.setText(agencyAccount);
        tvSaldoValor.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(userAccountLogin.getBalance())));

        ivHomeLogout = findViewById(R.id.ivHomeLogout);
        ivHomeLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        HomeScreenRequest request = new HomeScreenRequest();
        request.userId = userAccountLogin.getUserId();

        homeScreenInteractor.getStatementList(request);

        showProgress(getString(R.string.loadingTitle), getString(R.string.loadingMessage));
    }

    @Override
    public void showStatementList(HomeScreenResponse homeScreenResponse) {
        List<Statement> statementList = homeScreenResponse.statementList;
        Collections.sort(statementList, new Comparator<Statement>() {
            @Override
            public int compare(Statement st1, Statement st2) {
                return st2.getDate().compareTo(st1.getDate());
            }
        });

        dismissProgress();

        rcStatement = findViewById(R.id.rvHomeStatementList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcStatement.setLayoutManager(linearLayoutManager);
        statementAdapter = new StatementAdapter(this, statementList);
        rcStatement.setAdapter(statementAdapter);
    }


    public void showStatementList(List<Statement> statementList) {
        Collections.sort(statementList, new Comparator<Statement>() {
            @Override
            public int compare(Statement st1, Statement st2) {
                return st2.getDate().compareTo(st1.getDate());
            }
        });

        dismissProgress();

        rcStatement = findViewById(R.id.rvHomeStatementList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcStatement.setLayoutManager(linearLayoutManager);
        statementAdapter = new StatementAdapter(this, statementList);
        rcStatement.setAdapter(statementAdapter);
    }
}

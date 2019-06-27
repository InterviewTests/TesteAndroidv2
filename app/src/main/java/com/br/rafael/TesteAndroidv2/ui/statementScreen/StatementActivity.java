package com.br.rafael.TesteAndroidv2.ui.statementScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.rafael.TesteAndroidv2.R;
import com.br.rafael.TesteAndroidv2.Util.Constants;
import com.br.rafael.TesteAndroidv2.Util.Utils;
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;
import com.br.rafael.TesteAndroidv2.data.model.StatementResponse;

interface StatementActivityInput {

    void responselistStatemnt(StatementResponse response);
    void showProgressBar();
    void hideProgressBar();

}

public class StatementActivity extends AppCompatActivity implements StatementActivityInput {

    protected StatementIntaractorInput output;
    protected StatementRouter router;
    private LoginResponse login;

    RecyclerView recyclerRtatements;
    ProgressBar progressBar;
    TextView txtNameCliente;
    ImageView imgExit;
    TextView txtAccount;
    TextView txtNumberAccount;
    TextView txtBalance;
    TextView txtValueBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        StatementConfigurator.INSTANCE.configure(this);
        initView();
        login = getIntent().getParcelableExtra(Constants.INSTANCE.getExtraLoginResponse());

        initHeaderStatemt();
        setListenerExit();
        output.informationLogin(login);

    }

    private void initView() {

        recyclerRtatements = findViewById(R.id.list_statement);
        progressBar = findViewById(R.id.progressBar);
        txtNameCliente = findViewById(R.id.txt_name_cliente);
        imgExit = findViewById(R.id.img_exit);
        txtAccount = findViewById(R.id.txt_account);
        txtNumberAccount = findViewById(R.id.txt_number_account);
        txtBalance = findViewById(R.id.txt_balance);
        txtValueBalance = findViewById(R.id.txt_value_balance);

    }

    private void initHeaderStatemt() {

        if (null != login.getLogin().getName()) {
            txtNameCliente.setText(login.getLogin().getName());
        }

        if (null != login.getLogin().getBankAccount() && null != login.getLogin().getAgency()) {
            txtNumberAccount.setText(Utils.Companion.formatAgencyAccount(login.getLogin().getBankAccount(),
                    login.getLogin().getAgency()));
        }
        if (String.valueOf(login.getLogin().getBalance()).length() > 0) {
            txtValueBalance.setText(Utils.Companion.converMoney(login.getLogin().getBalance()));
        }

    }

    private void setListenerExit() {

        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void responselistStatemnt(StatementResponse response) {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setReverseLayout(false);
        recyclerRtatements.setLayoutManager(layoutManager);

        StatementAdapter adapter = new StatementAdapter(response.getStatementArrayList(), this);

        recyclerRtatements.setAdapter(adapter);

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}

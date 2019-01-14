package br.com.accenture.santander.wallacebaldenebre.ui.account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import br.com.accenture.santander.wallacebaldenebre.R;
import br.com.accenture.santander.wallacebaldenebre.model.Account;
import br.com.accenture.santander.wallacebaldenebre.model.StatementList;
import br.com.accenture.santander.wallacebaldenebre.ui.adapter.StatementsAdapter;
import br.com.accenture.santander.wallacebaldenebre.ui.base.BaseActivity;
import br.com.accenture.santander.wallacebaldenebre.ui.login.LoginActivity;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends BaseActivity {
    private AccountPresenter p;
    private ProgressDialog pd;
    private StatementsAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setUp();
        showData();
    }

    @Override
    protected void setUp() {
        p = new AccountPresenter();
        p.onAttach(this);

        rv = findViewById(R.id.rcv_bills);
    }

    @Override
    protected void showData() {
        p.showDataFromServer(this, new AccountCallback<HashMap<String, Account>>() {
            @Override
            public void onSuccess(HashMap<String, Account> data) {
                ArrayList<StatementList> statements = null;

                for (Map.Entry<String, Account> account : data.entrySet()) {
                    Account a = account.getValue();
                    statements = a.getStatementList();
                }

                final ArrayList<StatementList> finalStatements = statements;
                AccountActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new StatementsAdapter(finalStatements);
                        rv.setLayoutManager(new LinearLayoutManager(AccountActivity.this, LinearLayoutManager.VERTICAL, false));
                        rv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(int errorCode, String reason) {
                Helper.toast(AccountActivity.this, errorCode + ": " + reason);
            }
        });
    }

    @Override
    public void showProgress() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pd = new ProgressDialog(AccountActivity.this);
                pd.setTitle("Aguarde...");
                pd.setMessage("Carregando os dados...");
                pd.setCancelable(true);
                pd.show();

                Handler hpd = new Handler();
                hpd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void hideProgress() {
        if (pd.isShowing()) pd.dismiss();
    }


    @Override
    public void hideKeyboard() {

    }
}

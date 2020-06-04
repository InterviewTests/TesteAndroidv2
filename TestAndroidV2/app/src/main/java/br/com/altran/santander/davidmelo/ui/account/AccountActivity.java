package br.com.altran.santander.davidmelo.ui.account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import br.com.altran.santander.davidmelo.R;
import br.com.altran.santander.davidmelo.model.Account;
import br.com.altran.santander.davidmelo.model.StatementList;
import br.com.altran.santander.davidmelo.ui.adapter.StatementsAdapter;
import br.com.altran.santander.davidmelo.ui.base.BaseActivity;
import br.com.altran.santander.davidmelo.utils.Helper;

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

        rv = findViewById(R.id.list_payment);
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
                }, 3500);
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

    public void onExit(View view) {
    }
}

package br.com.fernandodutra.testeandroidv2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.adapters.StatementListAdapter;
import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

import java.text.NumberFormat;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 19/06/2019
 * Time: 12:29
 * TesteAndroidv2
 */
public class StatementListActivity extends AppCompatActivity implements StatementListContract.View {

    private Context context;
    private TextView et_name;
    private TextView et_accountagency;
    private TextView et_balance;
    private Button btn_close;

    private RecyclerView rv_statement;
    private StatementListAdapter statementListAdapter;
    private StatementListContract.Presenter statementListPresenter;

    private Integer userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statementlist_activity);

        setupComponents();
        setupActions();
        setupParameters();
    }

    private void setupParameters() {
        userId = (Integer) getIntent().getSerializableExtra(Constants.USERACCOUNT_USERID);

        statementListPresenter = new StatementListPresenter(this, new StatementListModel(), new UserAccountModel(context));
        statementListPresenter.getStatementList(userId);

        UserAccount userAccount = statementListPresenter.findUserAccount(userId);

        if (userAccount != null) {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            String balanceFormated = numberFormat.format(userAccount.getBalance());

            et_name.setText(userAccount.getName());
            et_accountagency.setText(userAccount.getAgency() + " / " + userAccount.getBankAccount());
            et_balance.setText(balanceFormated);
        }
    }

    private void setupActions() {
        btn_close.setOnClickListener(setOnClickListenerClose());
    }

    private void setupComponents() {
        context = StatementListActivity.this;

        et_name = findViewById(R.id.statement_activity_et_name);
        et_accountagency = findViewById(R.id.statement_activity_et_acoountagency);
        et_balance = findViewById(R.id.statement_activity_et_balance);
        btn_close = findViewById(R.id.statement_activity_btn_close);

        rv_statement = findViewById(R.id.statement_activity_rv_statement);
    }

    private View.OnClickListener setOnClickListenerClose() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLogin();
            }
        };
    }

    @Override
    public void setStatementListResponseToRecyclerView(StatementListResponse statementListResponse) {
        statementListAdapter = new StatementListAdapter();

        rv_statement.setLayoutManager(new LinearLayoutManager(this));
        rv_statement.setHasFixedSize(true);
        rv_statement.setAdapter(statementListAdapter);
        statementListAdapter.setStatementListsModel(statementListResponse);
    }

    public StatementListAdapter getStatementListAdapter(){
        return this.statementListAdapter;
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void returnLogin() {
        Intent intent = new Intent(getBaseContext(), UserAccountActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        returnLogin();
    }
}

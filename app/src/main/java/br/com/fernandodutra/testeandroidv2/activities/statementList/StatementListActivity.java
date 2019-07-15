package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.adapters.StatementListAdapter;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:01
 * TesteAndroidv2_CleanCode
 */

interface StatementListActivityInput {
    void displayLoginMetaData(StatementListViewModel statementListViewModel);
}

public class StatementListActivity extends AppCompatActivity implements StatementListActivityInput {

    private Context context;
    private TextView et_name;
    private TextView et_accountagency;
    private TextView et_balance;
    private Button btn_close;
    //
    StatementListInteractorInput statementListInteractorInput;
    StatementListRouter statementListRouter;
    StatementListWorker statementListWorker;
    //
    private RecyclerView rv_statement;
    private StatementListAdapter statementListAdapter;
    //
    private UserAccount userAccount;
    //
    public static String TAG = StatementList.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statementlist_activity);

        StatementListConfigurator.INSTANCE.configure(this);

        setupComponents();
        setupActions();
        setupParameters();

        fetchStatementListMetaData();
    }

    private void fetchStatementListMetaData() {
        statementListInteractorInput.fetchStatementListMetaData(userAccount.getUserId());
    }

    private void setupStatementListAdapterView() {
        statementListAdapter = new StatementListAdapter();

        rv_statement.setLayoutManager(new LinearLayoutManager(this));
        rv_statement.setHasFixedSize(true);
        rv_statement.setAdapter(statementListAdapter);
        statementListAdapter.setStatementListsModel(statementListWorker);
    }

    private void setupParameters() {
        userAccount = (UserAccount) getIntent().getSerializableExtra(Constants.USERACCOUNT_USERID);

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

    private View.OnClickListener setOnClickListenerClose() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLogin();
            }
        };
    }

    private void setupComponents() {
        context = StatementListActivity.this;

        et_name = findViewById(R.id.statement_activity_et_name);
        et_accountagency = findViewById(R.id.statement_activity_et_acoountagency);
        et_balance = findViewById(R.id.statement_activity_et_balance);
        btn_close = findViewById(R.id.statement_activity_btn_close);

        rv_statement = findViewById(R.id.statement_activity_rv_statement);
    }

    @Override
    public void displayLoginMetaData(StatementListViewModel statementListViewModel) {
        this.statementListWorker = statementListViewModel.statementListWorker;

        setupStatementListAdapterView();
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

    private void returnLogin() {
        statementListRouter.loginRouterInput();
    }
}

package br.com.dpassos.bankandroid.statements.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.dpassos.bankandroid.BaseView;
import br.com.dpassos.bankandroid.R;
import br.com.dpassos.bankandroid.infra.Asynchronous;
import br.com.dpassos.bankandroid.login.business.UserAccount;
import br.com.dpassos.bankandroid.statements.business.Statement;
import br.com.dpassos.bankandroid.statements.business.StatementControl;

public class StatementActivity extends BaseView {

    public TextView statementUser;
    public TextView statementAccount;
    public TextView statementMoney;
    public RecyclerView statementList;
    public ProgressBar statementLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statement_activity);
        statementList.setVisibility(View.GONE);
        retrieveLoginData();
        retrieveStatements();
    }

    void retrieveStatements() {
        StatementControl statementControl = new StatementControl();
        Asynchronous asynchronous = new Asynchronous();
        asynchronous.execute(() -> {
            try {
                UserAccount userAccount = statementControl.getCurrentAccout();
                List<Statement> statements = statementControl.getStatements(userAccount);
                List<StatementViewObject> viewObjectList = new ArrayList<>();
                for(Statement statement: statements) {
                    viewObjectList.add(new StatementViewObject(statement));
                }
                runOnUiSafe(()->showStatements(viewObjectList));
            } catch (StatementControl.StatementException e) {
                runOnUiSafe(()->Snackbar.make(statementUser, e.status.name(), Snackbar.LENGTH_SHORT).show());
            }finally {
                runOnUiSafe(()->statementLoadingIndicator.setVisibility(View.GONE));
            }
        });
    }

    void retrieveLoginData() {
        StatementControl statementControl = new StatementControl();
        Asynchronous asynchronous = new Asynchronous();
        asynchronous.execute(() -> {
            try {
                UserAccount userAccount = statementControl.getCurrentAccout();
                runOnUiSafe(()->showUserData(new UserAccountViewObject(userAccount)));
            } catch (StatementControl.StatementException e) {
                runOnUiSafe(()->Snackbar.make(statementUser, e.status.name(), Snackbar.LENGTH_SHORT).show());
            }
        });
    }

    void showUserData(UserAccountViewObject userAccount) {
        statementUser.setText(userAccount.statementUser);
        statementAccount.setText(userAccount.statementAccount);
        statementMoney.setText(userAccount.statementMoney);
    }

    void showStatements(List<StatementViewObject> statements) {
        StatementAdapter adapter = new StatementAdapter();
        adapter.statements = statements;

        statementList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        statementList.setLayoutManager(linearLayoutManager);
        statementList.setVisibility(View.VISIBLE);
    }
}

package monteoliva.testbank.view.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import monteoliva.testbank.R;
import monteoliva.testbank.controller.OnRestStatementListener;
import monteoliva.testbank.controller.RestStatement;
import monteoliva.testbank.controller.StatementList;
import monteoliva.testbank.controller.UserAccount;
import monteoliva.testbank.utils.Constantes;
import monteoliva.testbank.view.adapter.StatementAdapter;
import monteoliva.testbank.view.components.Progress;

@EActivity(R.layout.activity_statement)
@OptionsMenu(R.menu.menu_statement)
public class StatementActivity extends AppCompatActivity implements OnRestStatementListener {
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    protected ActionBar actionBar;

    @ViewById(R.id.account_number)
    protected TextView accountNumber;

    @ViewById(R.id.statement_list)
    protected RecyclerView statementList;

    @ViewById(R.id.statement_progress)
    protected Progress progress;

    private UserAccount userAccount;

    private RestStatement restStatement;

    @AfterViews
    protected void afterViews() {
        userAccount = (UserAccount) getIntent().getExtras().getSerializable(Constantes.ACCOUNT_KEY);


        // toolbar
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if ((actionBar != null) && (userAccount != null)) {
            actionBar.setTitle(userAccount.getName());

            accountNumber.setText(userAccount.getBankAccount() + " / " + getAgency(userAccount.getAgency()));
        }

        restStatement = new RestStatement();
        restStatement.setOnRestStatementListener(this);

        statementAsync();
    }

    @Background
    protected void statementAsync() { restStatement.send(userAccount.getUserId()); }

    @UiThread
    protected void result(@NonNull List<StatementList> list) {
        StatementAdapter adapter = new StatementAdapter(list);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        statementList.setLayoutManager(llm);
        statementList.setHasFixedSize(true);
        statementList.setAdapter(adapter);

        progress.hide();
    }

    @Override
    public void onSuccess(@NonNull List<StatementList>  list) { result(list); }

    @Override
    public void onError() {}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { return false; }
        else                                  { return super.onKeyDown(keyCode, event); }
    }

    /**
     * Method to format Agency Number
     *
     * @param value
     * @return
     */
    private String getAgency(@NonNull String value) {
        int total = value.length();

        String campo1 = "";
        String campo2 = "";
        String campo3 = "";

        for (int c = 0; c < total; c++) {
            String character = String.valueOf(value.charAt(c));

            if (c < 2) { campo1 += character; }
            else if (c < (total - 1)) {
                campo2 += character;
            }
            else {
                campo3 += character;
            }
        }

        return campo1 + "." + campo2 + "-" + campo3;
    }

    @OptionsItem(R.id.action_logout)
    protected void logout() {
        startActivity(new Intent(this, LoginActivity_.class));
        finish();
        overridePendingTransition( R.anim.lefttoright, R.anim.stable );
    }
}
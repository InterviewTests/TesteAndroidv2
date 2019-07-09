package ssilvalucas.testeandroidv2.screen.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ssilvalucas.testeandroidv2.R;
import ssilvalucas.testeandroidv2.data.model.StatementHome;
import ssilvalucas.testeandroidv2.data.model.StatementsResponse;
import ssilvalucas.testeandroidv2.data.model.UserAccount;
import ssilvalucas.testeandroidv2.util.FormatUtil;

interface HomeActivityInput {
    void showErrorMessage(String errorMessage);
    void responseStatements(StatementsResponse response);
}


public class HomeActivity extends AppCompatActivity implements HomeActivityInput {

    private TextView editTextName, editTextBankAccount, editTextBalance;
    private RecyclerView recyclerViewStatements;
    private ImageButton btnLogout;
    private UserAccount userAccount;
    private ArrayList<StatementHome> statementsList;

    protected HomeInteractorInput output;
    protected HomeRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeConfigurator.INSTANCE.configure(this);

        initViews();
        setDataAccount();
    }

    private void initViews(){
        editTextName           = findViewById(R.id.screen_home_user_name);
        editTextBankAccount    = findViewById(R.id.screen_home_bank_account);
        editTextBalance        = findViewById(R.id.screen_home_balance);
        recyclerViewStatements = findViewById(R.id.screen_home_recycler);
        btnLogout              = findViewById(R.id.screen_home_btn_logout);
        btnLogout.setOnClickListener(getOnClickLogout());
    }

    private void setDataAccount(){
        this.userAccount = getIntent().getParcelableExtra("userAccount");
        if (userAccount != null) {
            editTextName.setText(userAccount.getName());
            editTextBankAccount.setText(FormatUtil.formatBankAccount(userAccount.getAgency(), userAccount.getBankAccount()));
            editTextBalance.setText(FormatUtil.formatBalance(userAccount.getBalance()));
            output.fetchStatementsById(userAccount.getUserId());
        } else{
            this.finish();
        }
    }

    private View.OnClickListener getOnClickLogout(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void responseStatements(StatementsResponse response) {
        statementsList = response.getStatementArrayList();
    }
}

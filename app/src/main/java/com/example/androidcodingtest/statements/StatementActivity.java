package com.example.androidcodingtest.statements;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidcodingtest.login.LoginActivity;
import com.example.androidcodingtest.R;
import com.example.androidcodingtest.models.Statement;
import com.example.androidcodingtest.models.UserAccount;

import java.text.NumberFormat;
import java.util.List;

public class StatementActivity extends AppCompatActivity implements StatementInteractor.View{

    private StatementAdapter mAdapter;
    private UserAccount userAccount;
    private StatementPresenter presenter = new StatementPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        ImageView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        UserAccount userAccount = getIntent().getParcelableExtra("userAccount");
        if(userAccount != null){
            this.userAccount = userAccount;
            populateUserInfo();
        }
    }

    private void populateUserInfo() {

        TextView userName = findViewById(R.id.user_name);
        TextView userAccountNumber = findViewById(R.id.user_account_number);
        TextView userBalance = findViewById(R.id.user_balance);

        userName.setText(userAccount.getName());
        userAccountNumber.setText(userAccount.getBankAccount() + " / " + userAccount.getAgency());

        NumberFormat format = NumberFormat.getCurrencyInstance();
        userBalance.setText(format.format(userAccount.getBalance()));

        presenter.getStatements(userAccount.getUserId());
    }

    @Override
    public void generateStatementList(List<Statement> statementList) {
        mAdapter = new StatementAdapter(statementList);
        RecyclerView recyclerView = findViewById(R.id.statement_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }
}

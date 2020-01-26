package com.msbm.bank.accountDetailScreen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msbm.bank.BaseActivity;
import com.msbm.bank.R;
import com.msbm.bank.entities.Statement;

import java.util.List;

interface AccountDetailActivityInput {
    void displayStatementData(AccountDetailViewModel viewModel);
}

public class AccountDetailActivity extends BaseActivity implements AccountDetailActivityInput {

    public List<Statement> statements;

    protected AccountDetailInteractor accountDetailInteractor;

    private TextView txvName;
    private TextView txvAccountAgency;
    private TextView txvTotal;
    private RecyclerView rvwStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        getSupportActionBar().hide();
        bindViews();

        AccountDetailConfigurator.INSTANCE.configure(this);
    }

    @Override
    public void displayStatementData(AccountDetailViewModel viewModel) {
        statements = viewModel.statements;
    }

    private void bindViews() {
        txvName = findViewById(R.id.idName);
        txvAccountAgency = findViewById(R.id.idAccountNumberAgency);
        txvTotal = findViewById(R.id.idTotal);
        rvwStatement = findViewById(R.id.rvwStatement);
    }
}

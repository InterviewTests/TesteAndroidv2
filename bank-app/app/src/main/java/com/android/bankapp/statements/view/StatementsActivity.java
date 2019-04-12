package com.android.bankapp.statements.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.bankapp.R;
import com.android.bankapp.login.model.UserAccount;
import com.android.bankapp.statements.StatementConfigurator;
import com.android.bankapp.statements.interactor.StatementInteractor;
import com.android.bankapp.statements.model.Statement;
import com.android.bankapp.statements.view.adapter.StatementRecyclerAdapter;
import com.android.bankapp.util.UserStateUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.NumberFormat;
import java.util.List;

@SuppressLint("Registered")
@EActivity(R.layout.activity_steatements)
public class StatementsActivity extends AppCompatActivity implements StatementActivityInput {

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById(R.id.text_view_user_name)
    TextView textViewUserName;

    @ViewById(R.id.text_view_user_account)
    TextView textViewUserAccount;

    @ViewById(R.id.txt_view_user_balance)
    TextView textViewUserBalance;

    public StatementInteractor output;
    private StatementRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatementConfigurator.INSTANCE.configure(this);
    }

    @AfterViews
    void afterViews() {
        UserAccount user = UserStateUtil.getUserAccount();

        String account = formatUserAccount(user.getAgency(), user.getBankAccount());
        String balance = formatBalance(user.getBalance());

        textViewUserName.setText(user.getName());
        textViewUserAccount.setText(account);
        textViewUserBalance.setText("R$ " + String.valueOf(user.getBalance()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new StatementRecyclerAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        output.loadData();
    }

    private String formatBalance(Double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String balance = formatter.format(value);

        return "R" + balance;

    }

    private String formatUserAccount(String agency, String bankAccount) {
        String firstPart = agency.substring(0, 2);
        String secondPart = agency.substring(2, agency.length() - 2);

        agency = firstPart + "." + secondPart + "-" + agency.charAt(agency.length() - 1);

        return bankAccount + "/" + agency;
    }

    @Override
    public void dataLoaded(List<Statement> statementList) {
        adapter.addAll(statementList);
    }

    @Override
    public void errorLoadData() {

    }
}

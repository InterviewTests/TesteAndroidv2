package com.accenture.project.apptesteandroid.bankStatement;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.project.apptesteandroid.R;
import com.accenture.project.apptesteandroid.model.BankStatement;
import com.accenture.project.apptesteandroid.model.UserAccount;

import java.util.List;

interface IBankStatementActivity {

    void displayMessage(String message);
    void displayBankStatement(List<BankStatement> bankStatementList);

}


public class BankStatementActivity extends AppCompatActivity implements IBankStatementActivity {

    private BankStatementAdapter bankStatementAdapter;
    private RecyclerView rvStatementList;
    private TextView tvUserName, tvUserAccount, tvUserAgency, tvBalance, tvLblRecentes;
    private ProgressBar progressBar;
    BankStatementInteractor bankStatementInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);


        initViews();
        configureRecyclerView();
        BankStatementConfigurator.GET_INSTANCE.configure(this);
        displayUserAccountData();


    }

    private void initViews() {
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserAccount = findViewById(R.id.tv_account_number);
        tvUserAgency = findViewById(R.id.tv_agency);
        tvBalance = findViewById(R.id.tv_saldo);
        tvLblRecentes = findViewById(R.id.tv_lbl_recentes);
        rvStatementList = findViewById(R.id.rv_statement_list);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void configureRecyclerView() {
        //inicia o recyclerview e instância o adapter

        rvStatementList.setLayoutManager(new LinearLayoutManager(this));
        bankStatementAdapter = new BankStatementAdapter(this);
        rvStatementList.setAdapter(bankStatementAdapter);

    }

    private void displayUserAccountData() {
        UserAccount userAccount = (UserAccount) getIntent().getSerializableExtra("userAccount");
        if (userAccount != null) {

            tvUserName.setText(userAccount.getName());
            tvBalance.setText(userAccount.getBalance());
            tvUserAccount.setText(userAccount.getBankAccount());
            tvUserAgency.setText(userAccount.getAgency());

            bankStatementInteractor.fetchBankStatementByUserId(userAccount.getUserId());

        }
    }

    @Override
    public void displayBankStatement(List<BankStatement> bankStatementList) {
        progressBar.setVisibility(View.GONE);
        tvLblRecentes.setVisibility(View.VISIBLE);
        rvStatementList.setVisibility(View.VISIBLE);
        bankStatementAdapter.setItems(bankStatementList);
    }

    @Override
    public void displayMessage(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        dialogLogout();
    }

    public void onCLickLogout(View view) {
        dialogLogout();
    }

    public void dialogLogout(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Atenção");
        dialog.setMessage("Deseja encerrar a sessão?");
        dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("NÃO", null);
        dialog.show();
    }
}

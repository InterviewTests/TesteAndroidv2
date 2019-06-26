package com.everis.TesteAndroidv2.Statement.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.everis.TesteAndroidv2.Statement.Model.Statement;
import com.everis.TesteAndroidv2.Login.View.LoginActivity;
import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.Repository.RetrofitConfig;
import com.everis.TesteAndroidv2.Statement.Model.TransactionInfo;
import com.everis.TesteAndroidv2.Login.Model.UserAccount;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementActivity extends AppCompatActivity {

    TextView tvName, tvAccount, tvBalance;
    ImageButton ibLogout;
    RecyclerView rvStatement;
    UserAccount userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        userAccount = (UserAccount) getIntent().getSerializableExtra(getString(R.string.useraccount));
        initVars();
        carregarDadosPessoais();
        carregarExtrato();
    }

    void initVars() {
        tvName = findViewById(R.id.tv_statement_name);
        tvAccount = findViewById(R.id.tv_statement_account_number);
        tvBalance = findViewById(R.id.tv_statement_balance_value);
        ibLogout = findViewById(R.id.ib_statement_logout);
        rvStatement = findViewById(R.id.rv_statement);

        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutQuestion();
            }
        });
    }

    @Override
    public void onBackPressed() {
        logoutQuestion();
    }

    void logoutQuestion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja encerrar sua sessão?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton("Não", null)
                .setCancelable(true);
        builder.show();
    }

    void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void carregarDadosPessoais(){
        tvName.setText(userAccount.getName());
        tvAccount.setText(
                userAccount.getBankAccount() + " / "
                + userAccount.getAgency().substring(0,2) + "."
                + userAccount.getAgency().substring(2,8) + "-"
                + userAccount.getAgency().substring(8)
        );
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        tvBalance.setText(format.format(userAccount.getBalance()));
    }

    private void carregarExtrato() {
        Call<Statement> call = new RetrofitConfig().getConnectionService().checkStatementGET(userAccount.getUserId());
        call.enqueue(new Callback<Statement>() {
            @Override
            public void onResponse(@NonNull Call<Statement> call, @NonNull Response<Statement> response) {
                Statement lanc = response.body();
                assert lanc != null;
                if (!lanc.getStatementList().isEmpty()) {
                    setupRecycler(lanc.getStatementList());
                } else if (lanc.getError().getCode() == null) {
                    Toast.makeText(StatementActivity.this, "Extrato vazio", Toast.LENGTH_LONG).show();
                } else {
                    Float code = lanc.getError().getCode();
                    String message = lanc.getError().getMessage();
                    Toast.makeText(
                            StatementActivity.this,
                            "Falha ao exibir extrato.\nCódigo: " + code + "\nMensagem: " + message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<Statement> call, @NonNull Throwable t) {
                Toast.makeText(StatementActivity.this, "Falha de conexão", Toast.LENGTH_SHORT).show();
                Log.e("ConnectionService   ", "Erro de conexão: " + t.getMessage());
            }
        });
    }

    private void setupRecycler(ArrayList<TransactionInfo> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStatement.setLayoutManager(layoutManager);
        LineAdapter adapter = new LineAdapter(list);
        rvStatement.setAdapter(adapter);
    }
}
package com.everis.TesteAndroidv2.Extrato.View;

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

import com.everis.TesteAndroidv2.Extrato.Model.Lancamento;
import com.everis.TesteAndroidv2.Login.View.LoginActivity;
import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.Repository.RetrofitConfig;
import com.everis.TesteAndroidv2.Extrato.Model.Statements;
import com.everis.TesteAndroidv2.Login.Model.UserAccount;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtratoActivity extends AppCompatActivity {

    TextView tvNome, tvConta, tvSaldo;
    ImageButton ibSair;
    RecyclerView rv_extrato;
    UserAccount ua;

    private LineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
        ua = (UserAccount) getIntent().getSerializableExtra("useraccount");
        initVars();
        carregarDadosPessoais();
        carregarExtrato();
    }

    void initVars() {
        tvNome = findViewById(R.id.tv_extrato_nome);
        tvConta = findViewById(R.id.tv_extrato_conta_num);
        tvSaldo = findViewById(R.id.tv_extrato_saldo_num);
        ibSair = findViewById(R.id.ib_extrato_sair);
        rv_extrato = findViewById(R.id.rv_extrato);

        ibSair.setOnClickListener(new View.OnClickListener() {
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
        tvNome.setText(ua.getName());
        tvConta.setText(
                ua.getBankAccount() + " / "
                + ua.getAgency().substring(0,2) + "."
                + ua.getAgency().substring(2,8) + "-"
                + ua.getAgency().substring(8)
        );
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        tvSaldo.setText(format.format(ua.getBalance()));
    }

    private void carregarExtrato() {
        Call<Lancamento> call = new RetrofitConfig().getConnectionService().checarExtrato(ua.getUserId());
        call.enqueue(new Callback<Lancamento>() {
            @Override
            public void onResponse(@NonNull Call<Lancamento> call, @NonNull Response<Lancamento> response) {
                Lancamento lanc = response.body();
                assert lanc != null;
                if (!lanc.getStatementList().isEmpty()) {
                    setupRecycler(lanc.getStatementList());
                } else if (lanc.getError().getCode() == null) {
                    Toast.makeText(ExtratoActivity.this, "Extrato vazio", Toast.LENGTH_LONG).show();
                } else {
                    Float code = lanc.getError().getCode();
                    String message = lanc.getError().getMessage();
                    Toast.makeText(
                            ExtratoActivity.this,
                            "Falha ao exibir extrato.\nCódigo: " + code + "\nMensagem: " + message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<Lancamento> call, @NonNull Throwable t) {
                Toast.makeText(ExtratoActivity.this, "Falha de conexão", Toast.LENGTH_SHORT).show();
                Log.e("ConnectionService   ", "Erro de conexão: " + t.getMessage());
            }
        });
    }

    private void setupRecycler(ArrayList<Statements> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_extrato.setLayoutManager(layoutManager);
        mAdapter = new LineAdapter(list);
        rv_extrato.setAdapter(mAdapter);
    }
}
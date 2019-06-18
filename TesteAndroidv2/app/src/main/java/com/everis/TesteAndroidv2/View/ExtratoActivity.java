package com.everis.TesteAndroidv2.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.everis.TesteAndroidv2.Lancamento;
import com.everis.TesteAndroidv2.LineAdapter;
import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.RetrofitConfig;
import com.everis.TesteAndroidv2.Statements;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtratoActivity extends AppCompatActivity {

    TextView tvNome, tvConta, tvSaldo;
    ImageButton ibSair;
    RecyclerView rv_extrato;

    private LineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
        initVars();
        carregaExtrato();
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void carregaExtrato() {
        Call<Lancamento> call = new RetrofitConfig().getStatementService().checarExtrato("1"); //TODO: puxar idUser de Login
        call.enqueue(new Callback<Lancamento>() {
            @Override
            public void onResponse(@NonNull Call<Lancamento> call, @NonNull Response<Lancamento> response) {
                Lancamento lanc = response.body();
                assert lanc != null;
                if (!lanc.getStatementList().isEmpty()) {
                    setupRecycler(lanc.getStatementList());
                } else {
                    Float code = lanc.getError().getCode();
                    String message = lanc.getError().getMessage();
                    Toast.makeText(
                            ExtratoActivity.this,
                            "Erro: Extrato vazio.\nCódigo: " + code + "\nMensagem: " + message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<Lancamento> call, @NonNull Throwable t) {
                Toast.makeText(ExtratoActivity.this, "Falha ao carregar o extrato", Toast.LENGTH_SHORT).show();
                Log.e("StatementService   ", "Erro ao buscar o lançamento: " + t.getMessage());
            }
        });
    }

    private void setupRecycler(ArrayList<Statements> list){

        //Configurando o gerenciador de layout para ser uma lista
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_extrato.setLayoutManager(layoutManager);

        //adiciona o adapter que irá anexar os objetos à lista.
        //Está sendo criada com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter(list);
        rv_extrato.setAdapter(mAdapter);
    }
}
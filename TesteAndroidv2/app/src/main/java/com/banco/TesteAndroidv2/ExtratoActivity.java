package com.banco.TesteAndroidv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExtratoActivity extends AppCompatActivity {

    TextView tvNome, tvConta, tvSaldo;
    ImageButton ibSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
        initVars();
    }

    void initVars(){
        tvNome = findViewById(R.id.tv_extrato_nome);
        tvConta = findViewById(R.id.tv_extrato_conta_num);
        tvSaldo = findViewById(R.id.tv_extrato_saldo_num);
        ibSair = findViewById(R.id.ib_extrato_sair);

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
}

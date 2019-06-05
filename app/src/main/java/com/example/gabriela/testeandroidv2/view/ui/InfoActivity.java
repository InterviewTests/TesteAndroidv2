package com.example.gabriela.testeandroidv2.view.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabriela.testeandroidv2.R;
import com.example.gabriela.testeandroidv2.presenter.InfoPresenter;
import com.example.gabriela.testeandroidv2.view.contact.InfoInterface;

import java.text.NumberFormat;

public class InfoActivity extends AppCompatActivity implements InfoInterface {

    TextView textName, textNumber, textSaldo;
    String nome, numeroConta, saldo;
    ListView listTransactions;
    ImageView imgSair;
    InfoPresenter infoPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        numeroConta = intent.getStringExtra("conta");
        saldo = intent.getStringExtra("saldo");

        textName = findViewById(R.id.text_name);
        textNumber = findViewById(R.id.text_number);
        textSaldo = findViewById(R.id.text_saldo);
        listTransactions = findViewById(R.id.list_transactions);
        imgSair = findViewById(R.id.img_sair);

        textName.setText(nome);
        textNumber.setText(numeroConta);
        textSaldo.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(saldo)));

        infoPresenter = new InfoPresenter(InfoActivity.this, this);
        infoPresenter.showTrasactions(listTransactions);

        imgSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Carregando");
        progressDialog.setMessage("Buscando Transações...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}

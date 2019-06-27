package com.example.projectsantander.transacao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsantander.R;
import com.example.projectsantander.login.Login;
import com.example.projectsantander.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class TransacaoActivity extends AppCompatActivity implements TransacaoContract.TransacaoView {

    private TextView txtNome;
    private TextView txtConta;
    private TextView txtSaldo;
    private ImageButton btnSair;
    private ListView lstTransacoes;
    private ListTransacoesAdapter adapter;
    private List<Transacao> lista;
    private TransacaoContract.TransacaoModel model;
    private TransacaoContract.TransacaoPresenter presenter;
    private Login login;
    private ProgressDialog progressDialog;
    Transacao transacao = new Transacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao);

        vinculaAtributos();
        vincularEventos();
        presenter = new TransacaoPresenterImpl(this);
        presenter.setDados(login);
    }

    private void vincularEventos() {
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sair();
            }
        });
    }

    private void vinculaAtributos() {

        txtNome = findViewById(R.id.txtNome);
        txtConta = findViewById(R.id.txtConta);
        txtSaldo = findViewById(R.id.txtSaldo);
        btnSair = findViewById(R.id.btnSair);
        lstTransacoes = findViewById(R.id.lstTransacoes);
        lista = new ArrayList<>();
        adapter = new ListTransacoesAdapter(lista, this);
        lstTransacoes.setAdapter(adapter);

        login = (Login) getIntent().getExtras().get("dados");

    }

    @Override
    public void preencherNome(String nome) {
        txtNome.setText(nome);
    }

    @Override
    public void preencherDadosBancarios(String dados) {
        txtConta.setText(dados);
    }

    @Override
    public void preencherSaldo(String saldo) {
        txtSaldo.setText(saldo);
    }

    @Override
    public void exibeMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void exibeLoading(String msg) {
        this.progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void fechaLoading() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void preencheLista(List<Transacao> lista) {
        this.lista.clear();
        this.lista.addAll(lista);
        if(lstTransacoes != null){
            String a = String.valueOf(transacao.getValue());
            String b = a.replace("(", "-");
            String c = a.replace(")", "");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void chamarLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}

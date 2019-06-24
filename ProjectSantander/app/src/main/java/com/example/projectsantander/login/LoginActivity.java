package com.example.projectsantander.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectsantander.R;
import com.example.projectsantander.services.LoginResponse;
import com.example.projectsantander.transacao.TransacaoActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{

    private EditText edtUsuario;
    private EditText edtSenha;
    private CardView btnLogin;
    private LoginContract.LoginPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vinculaAtributos();
        vinculaEventos();
        edtUsuario.requestFocus();
    }

    private void vinculaAtributos() {
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        presenter = new LoginPresenterImpl(this);
    }

    private void vinculaEventos(){
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();
                presenter.realizaLogin(usuario, senha);
            }
        });
    }

    @Override
    public void exibeMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void focarUsuario() {
        edtUsuario.requestFocus();
    }

    @Override
    public void focarSenha() {
        edtSenha.requestFocus();
    }

    @Override
    public void exibeLoading(String msg) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void fechaLoading() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void chamaTelaTransacoes(Login body) {
        Intent intent = new Intent(this, TransacaoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dados", body);
        intent.putExtras(bundle);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void limparCampos() {
        this.edtUsuario.getText().clear();
        this.edtSenha.getText().clear();
    }
}

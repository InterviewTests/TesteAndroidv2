package com.example.gabriela.testeandroidv2.view.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriela.testeandroidv2.R;
import com.example.gabriela.testeandroidv2.interfaces.IRetrofitCEP;
import com.example.gabriela.testeandroidv2.model.LoginRes;
import com.example.gabriela.testeandroidv2.presenter.MainActivityPresenter;
import com.example.gabriela.testeandroidv2.view.contact.MainActivityInterface;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    Button buttonLogin;
    EditText editSenha, editUser;
    String user, senha;
    MainActivityPresenter mainActivityPresenter;
    ProgressDialog progressDialog;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonLogin = findViewById(R.id.buttonLogin);
        editSenha = findViewById(R.id.editSenha);
        editUser = findViewById(R.id.editUser);

        mainActivityPresenter = new MainActivityPresenter(MainActivity.this, this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = editUser.getText().toString();
                senha = editSenha.getText().toString();
                mainActivityPresenter.login( editUser, editSenha);
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void redirectActivity(Context context, Class<?> willgo, String nome, String conta, String saldo){
        Intent intent = new Intent(context, willgo);
        intent.putExtra("nome", nome);
        intent.putExtra("conta", conta);
        intent.putExtra("saldo", saldo);
        startActivity(intent);
    }

    @Override
    public void showProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Carregando");
        progressDialog.setMessage("Iniciando aplicativo...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aviso");
        builder.setMessage("Você realmente deseja sair do aplicativo?");
        builder.setIcon(R.drawable.ic_warning);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();

    }
}

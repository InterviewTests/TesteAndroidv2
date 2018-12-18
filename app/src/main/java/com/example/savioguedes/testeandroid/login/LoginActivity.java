package com.example.savioguedes.testeandroid.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savioguedes.testeandroid.R;
import com.example.savioguedes.testeandroid.state.StateActivity;
import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.UserAccount;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    LoginPresenter loginPresenter;
    UserAccount userAccount;
    Login login;
    EditText username, password;
    Button btnlogin;
    ProgressDialog progressDialog;
    String userText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = new Login();
        userAccount = new UserAccount();
        loginPresenter = new LoginPresenter(this, getApplicationContext());
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!loginPresenter.getPreferences("CURRENT_USERNAME").isEmpty()){

            username.setText(loginPresenter.getPreferences("CURRENT_USERNAME"));
        }
    }

    @Override
    public void initView() {

        username = findViewById(R.id.login_field_user);
        password = findViewById(R.id.login_field_passrword);
        btnlogin = findViewById(R.id.login_button_login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //username = test_user ##### password = Test@1

                userText = username.getText().toString();
                passText = password.getText().toString();

                login.setUser(userText);
                login.setPassword(passText);

                showProgressDialog();
                loginPresenter.getLoginExecute(login);
            }
        });
    }

    @Override
    public void passUserinfo(int id, String name, String account, String balance) {

        progressDialog.dismiss();

        Intent intent = new Intent(LoginActivity.this, StateActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("NOME", name);
        intent.putExtra("CONTA", account);
        intent.putExtra("SALDO", balance);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgressDialog() {

        progressDialog.setTitle("Login");
        progressDialog.setMessage("Verificando credenciais...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.show();
    }

    @Override
    public void onErroRequest() {

        progressDialog.dismiss();

        Toast.makeText(this, "Erro ao realizar login", Toast.LENGTH_LONG).show();
    }

}

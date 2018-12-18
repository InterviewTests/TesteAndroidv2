package com.example.jcsantos.santanderteste.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jcsantos.santanderteste.Objects.User;
import com.example.jcsantos.santanderteste.R;
import com.example.jcsantos.santanderteste.Statements.StatementActivity;
import com.example.jcsantos.santanderteste.Utils.CpfValidate;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements LoginResponse {
    EditText name_user;
    EditText pass_user;
    Button btn_login;
    User user_data;
    LoginModel model;
    CpfValidate cpfValidate;
    Boolean a;
    boolean erroPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name_user = findViewById(R.id.user_acess);
        pass_user = findViewById(R.id.user_pass);
        btn_login = findViewById(R.id.btn_login);
        user_data = new User();
        cpfValidate = new CpfValidate();
        model = new LoginModel(this, this);

        handleLogin();
    }

    void handleLogin() {
            btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erroPass = cpfValidate.checkPassword(pass_user.getText().toString());
                if (name_user.getText().toString().equals("") || pass_user.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Os campos devem ser preenchidos.",Toast.LENGTH_LONG).show();
                }else if (!erroPass) {
                    cpfValidate.checkPassword("");
                    pass_user.setText("");
                    new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Senha inválida")
                        .setMessage("A senha deve conter ao menos um número, uma letra maiúscula e um caractere especial.")
                        .setPositiveButton(android.R.string.ok, null).show();
                } else {
                    model.requestLogin(name_user.getText().toString(), pass_user.getText().toString());
                }
                // a = cpfValidate.isValid(name_user.getText().toString());
            }

        });
    }

    @Override
    public void loginSucess(User user) {
        if (user.getName() != null) {
            Context cx = getApplicationContext();
            Intent intent = new Intent(cx, StatementActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("user_info", user);
            cx.startActivity(intent);
        }
    }
}

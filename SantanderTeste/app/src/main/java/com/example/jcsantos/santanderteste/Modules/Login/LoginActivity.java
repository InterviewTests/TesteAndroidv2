package com.example.jcsantos.santanderteste.Modules.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jcsantos.santanderteste.Components.Objects.User;
import com.example.jcsantos.santanderteste.R;
import com.example.jcsantos.santanderteste.Modules.Statements.StatementActivity;
import com.example.jcsantos.santanderteste.Components.Utils.ItemsValidate;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginResponse {
    EditText name_user;
    EditText pass_user;
    Button btn_login;
    User user_data;
    LoginModel model;
    ItemsValidate cpfValidate;
    boolean erroPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name_user = findViewById(R.id.user_acess);
        pass_user = findViewById(R.id.user_pass);
        btn_login = findViewById(R.id.btn_login);
        user_data = new User();
        cpfValidate = new ItemsValidate();
        model = new LoginModel(this, this);

        checkLastUser();
    }

    void checkLastUser() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        if (preferences.getString(User.USER_ACCESS, null) != null){
            name_user.setText(preferences.getString(User.USER_ACCESS, null));
        }
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
                    startActivityIndicator();
                    model.requestLogin(name_user.getText().toString(), pass_user.getText().toString());
                }
            }

        });
    }

    private void saveUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(User.USER_ACCESS, name_user.getText().toString());
        editor.apply();
    }

    void startActivityIndicator() {
        findViewById(R.id.progress_login).setVisibility(View.VISIBLE);
    }
    void stopActivityIndicator() {
        findViewById(R.id.progress_login).setVisibility(View.GONE);
    }

    @Override
    public void loginSucess(User user) {
        stopActivityIndicator();
        if (user.getUserId() > -1) {
            saveUser(LoginActivity.this);
            Context cx = getApplicationContext();
            Intent intent = new Intent(cx, StatementActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("user_info", user);
            cx.startActivity(intent);
        }
    }
}

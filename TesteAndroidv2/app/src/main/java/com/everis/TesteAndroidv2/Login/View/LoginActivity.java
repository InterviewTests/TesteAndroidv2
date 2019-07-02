package com.everis.TesteAndroidv2.Login.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.everis.TesteAndroidv2.Login.Model.LoginData;
import com.everis.TesteAndroidv2.Login.Model.UserAccount;
import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.Repository.RetrofitConfig;
import com.everis.TesteAndroidv2.Base.Utils.Validators;
import com.everis.TesteAndroidv2.Statement.View.StatementActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etUser, etPassword;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initVars();
    }

    private void initVars() {
        sharedPreferences = getSharedPreferences(getString(R.string.User),MODE_PRIVATE);

        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        etUser.setText(loadLoginField());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();

                if (Validators.isValidCPF(user) || Validators.isEmail(user)) {
                    if (Validators.isValidPassword(password)) {
                        checkLogin(user, password);
                    } else {
                        Toast.makeText(LoginActivity.this, "Senha não confere com os padrões", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "CPF ou e-mail inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLogin(final String user, String password){
        Call<LoginData> call = new RetrofitConfig().getConnectionService().checkLoginPOST(user, password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                LoginData loginData = response.body();
                assert loginData != null;
                if (loginData.getUserAccount().getUserId() != null) {
                    UserAccount userAccount = loginData.getUserAccount();
                    saveLoginField(user);
                    nextActivity(userAccount);
                } else if (loginData.getError().getCode() != null){
                    Float code = loginData.getError().getCode();
                    String message = loginData.getError().getMessage();
                    Toast.makeText(
                            LoginActivity.this,
                            "Falha ao se autenticar.\nCódigo: " + code + "\nMensagem: " + message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<LoginData> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    String loadLoginField(){
        sharedPreferences = getSharedPreferences(getString(R.string.useraccount), MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.useraccount), "");
    }

    void saveLoginField(String user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.useraccount), user);
        editor.apply();
    }

    void nextActivity(UserAccount userAccount){
        Intent intent = new Intent(LoginActivity.this, StatementActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.useraccount), userAccount);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
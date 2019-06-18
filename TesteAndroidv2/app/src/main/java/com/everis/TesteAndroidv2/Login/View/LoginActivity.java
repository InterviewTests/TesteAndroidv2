package com.everis.TesteAndroidv2.Login.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.everis.TesteAndroidv2.Login.Model.DadosLogin;
import com.everis.TesteAndroidv2.Login.Model.UserAccount;
import com.everis.TesteAndroidv2.R;
import com.everis.TesteAndroidv2.Repository.RetrofitConfig;
import com.everis.TesteAndroidv2.Base.Utils.Validadores;
import com.everis.TesteAndroidv2.Extrato.View.ExtratoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etUser, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
    }

    private void initVars() {
        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();

                if (Validadores.isValidCPF(user) || Validadores.isEmail(user)) {
                    if (Validadores.isValidPassword(password)) {
                        validarLogin(user, password);
                    } else {
                        Toast.makeText(LoginActivity.this, "Senha não confere com os padrões", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "CPF ou e-mail inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validarLogin(final String user, String password){
        Call<DadosLogin> call = new RetrofitConfig().getConnectionService().checarLogin(user, password);
        call.enqueue(new Callback<DadosLogin>() {
            @Override
            public void onResponse(@NonNull Call<DadosLogin> call, @NonNull Response<DadosLogin> response) {
                DadosLogin dl = response.body();
                assert dl != null;
                if (dl.getUserAccount().getUserId() != null) {
                    UserAccount ua = dl.getUserAccount();
                    proximaTela(ua);
                } else if (dl.getError().getCode() != null){
                    Float code = dl.getError().getCode();
                    String message = dl.getError().getMessage();
                    Toast.makeText(
                            LoginActivity.this,
                            "Falha ao se autenticar.\nCódigo: " + code + "\nMensagem: " + message,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(@Nullable Call<DadosLogin> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void proximaTela(UserAccount ua){
        Intent intent = new Intent(LoginActivity.this, ExtratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("useraccount", ua);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
package com.example.santanderapp.santander;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santanderapp.santander.interfaceService.LoginService;
import com.example.santanderapp.santander.model.RequestLogin;
import com.example.santanderapp.santander.model.ResponseLogin;
import com.example.santanderapp.santander.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtUser;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configureUI();

        verifyHasSavedLogin();

        btnLogin.setOnClickListener(listenerLogin);


    }

    //Pegando o último usuário salvo, caso não tiver deixa em branco
    private void verifyHasSavedLogin() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.userAccount), MODE_PRIVATE);

        edtUser.setText(preferences.getString(getString(R.string.user), ""));
    }

    private void configureUI() {
        btnLogin = findViewById(R.id.btnLogin);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    //Valida se no campo user é um CPF ou e-mail válido e o campo password constam as regras mínimas
    private boolean validatesData() {
        if ((!Utils.isEmailValid(edtUser.getText().toString())) && (!Utils.isCpfValid(edtUser.getText().toString()))) {
            Toast.makeText(LoginActivity.this, getString(R.string.errorLoginUser), Toast.LENGTH_SHORT).show();
            edtUser.requestFocus();
            return true;
        }
        if (!Utils.isPasswordValid(edtPassword.getText().toString())) {
            Toast.makeText(LoginActivity.this, getString(R.string.errorLoginPassword), Toast.LENGTH_SHORT).show();
            edtPassword.requestFocus();
            return true;
        }
        return true;
    }

    //Evento de onclick do botão Login
    private View.OnClickListener listenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService service = retrofit.create(LoginService.class);

            RequestLogin requestLogin = new RequestLogin();

            requestLogin.user = edtUser.getText().toString();
            requestLogin.password = edtPassword.getText().toString();
            if (validatesData()) {
                Call<ResponseLogin> requestCatalog = service.login(requestLogin);

                requestCatalog.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (!response.isSuccess()) {
                            Toast.makeText(LoginActivity.this, getString(R.string.error) + response.code(), Toast.LENGTH_SHORT).show();

                        } else {
                            ResponseLogin dateClient = response.body();

                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.userAccount), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putInt(getString(R.string.userId), dateClient.userAccount.userId);
                            editor.putString(getString(R.string.name), dateClient.userAccount.name);
                            editor.putString(getString(R.string.bankAccount), dateClient.userAccount.bankAccount);
                            editor.putString(getString(R.string.agency), dateClient.userAccount.agency);
                            editor.putFloat(getString(R.string.balance), dateClient.userAccount.balance);

                            editor.putString(getString(R.string.user), edtUser.getText().toString());

                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, DetailsActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        if (!Utils.isConected(LoginActivity.this)) {
                            Toast.makeText(LoginActivity.this, getString(R.string.notConnectInternet), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(LoginActivity.this, getString(R.string.fail) + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    };

}



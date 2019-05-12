package com.example.santanderapp.santander.homeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santanderapp.santander.R;
import com.example.santanderapp.santander.detailScreen.DetailsActivity;
import com.example.santanderapp.santander.homeScreen.interfaceService.LoginService;
import com.example.santanderapp.santander.homeScreen.model.RequestLogin;
import com.example.santanderapp.santander.homeScreen.model.ResponseLogin;
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
    private ProgressDialog progress;

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
    public static boolean validatesData(String edtUser, String edtPassword) {
        if ((!Utils.isEmailValid(edtUser)) && (!Utils.isCpfValid(edtUser))) {
            return false;
        }
        if (!Utils.isPasswordValid(edtPassword)) {
            return false;
        }
        return true;
    }

    //Evento de onclick do botão Login
    private View.OnClickListener listenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            progress = new ProgressDialog(LoginActivity.this);
            progress.setTitle(getString(R.string.loading));
            progress.setMessage(getString(R.string.whaitLoading));
            progress.setCancelable(false);
            progress.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService service = retrofit.create(LoginService.class);

            RequestLogin requestLogin = new RequestLogin();

            requestLogin.user = edtUser.getText().toString();
            requestLogin.password = edtPassword.getText().toString();
            if (validatesData(edtUser.getText().toString(),  edtPassword.getText().toString())) {
                Call<ResponseLogin> requestCatalog = service.login(requestLogin);

                requestCatalog.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        progress.dismiss();
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
            else {
                progress.dismiss();
                Toast.makeText(LoginActivity.this, getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
            }
        }
    };

}



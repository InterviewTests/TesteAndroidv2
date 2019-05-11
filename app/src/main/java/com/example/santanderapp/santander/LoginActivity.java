package com.example.santanderapp.santander;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santanderapp.santander.interfaceService.LoginService;
import com.example.santanderapp.santander.model.RequestLogin;
import com.example.santanderapp.santander.model.ResponseLogin;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.M)
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

                Call<ResponseLogin> requestCatalog = service.login(requestLogin);

                requestCatalog.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (!response.isSuccess()) {
                            Toast.makeText(LoginActivity.this, "Erro: " + response.code(), Toast.LENGTH_SHORT).show();

                        } else {
                            ResponseLogin dateClient = response.body();

                            Intent intent = new Intent(LoginActivity.this, DetailsActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Falhou: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                //new RealizaLoginTask(MainActivity.this, usuario).execute();

            }
        });


    }

    private void configureUI() {
        btnLogin = findViewById(R.id.btnLogin);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }


}

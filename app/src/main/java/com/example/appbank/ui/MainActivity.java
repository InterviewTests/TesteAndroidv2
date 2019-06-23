package com.example.appbank.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbank.R;
import com.example.appbank.data.remote.ILoginService;
import com.example.appbank.data.remote.ServiceGenerator;
import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView textUser;
    private TextView textPassword;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUi();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesUser();
                LoginRequest loginRequest = new LoginRequest(textUser.getText().toString(), textPassword.getText().toString());
                login(loginRequest);

            }
        });

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(preferences.contains("user")){

            String user = preferences.getString("user", "User");
            textUser.setText(user);

        }else{
            textUser.setText("User");
        }

    }

    private void login(LoginRequest loginRequest) {
        //preparando a minha classe de serviço para fazer uma chamada Rest
        ILoginService loginService = ServiceGenerator.createService(ILoginService.class);
        //Criar a chamada do endPoint que eu preciso
        Call<LoginResponse> call = loginService.postLogin(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getError().getCode() != 0) {
                        Toast.makeText(getApplicationContext(), loginResponse.getError().getMassage(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("Id", loginResponse.getUserAccount().getUserId());
                    b.putString("Name", loginResponse.getUserAccount().getName());
                    b.putString("Agency", loginResponse.getUserAccount().getAgency());
                    b.putString("BankAccount", loginResponse.getUserAccount().getBankAccount());
                    b.putDouble("Balance", loginResponse.getUserAccount().getBalance());
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Oops! Algo de errado aconteceu!", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void loadUi() {
        textPassword = findViewById(R.id.textPassword);
        textUser = findViewById(R.id.textUser);
    }

    private void sharedPreferencesUser() {

        //somente o app que vai conseguir salvar e ler o arquivo = modo 0
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (textUser.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Preencha o usuário", Toast.LENGTH_LONG).show();
        } else {
            String user = textUser.getText().toString();
            editor.putString("user", user);
            editor.commit();
            textUser.setText(user);
        }
    }
}

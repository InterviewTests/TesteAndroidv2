package com.project.personal.app_bank.activities;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.personal.app_bank.API.APIInterface;
import com.project.personal.app_bank.API.RetrofitClient;
import com.project.personal.app_bank.R;
import com.project.personal.app_bank.models.LoginRequest;
import com.project.personal.app_bank.models.UserResponse;
import com.project.personal.app_bank.utils.CheckPassword;
import com.project.personal.app_bank.utils.CheckUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText user, password;
    private Button buttonLogin;
    private APIInterface apiInterface;
    private Intent intent;
    private String userName, userAccount, userBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para requisições get e post
        apiInterface = RetrofitClient.getInstance().create(APIInterface.class);

        userName = null;
        userAccount = null;
        userBalance = null;

        user = findViewById(R.id.editUser);
        password = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(CheckUser.checkUser(user.getText().toString())==true){

                    //validando a senha
                    if(CheckPassword.validPassword(password.getText().toString()) == true) {

                        login();

                    }else{
                        Toast.makeText(MainActivity.this, "A senha deve possuir pelo menos um caracter Maiúsculo, um especial e um alfanumérico", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "O usuário pode ser seu e-mail ou CPF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void login(){
            //logar usuário
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUser(user.toString());
            loginRequest.setPassword(password.toString());
            Call<UserResponse> userResponse = apiInterface.createPost(loginRequest);


            userResponse.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    UserResponse userResponse = response.body();

                    userName = userResponse.getUserAccount().getName();
                    userAccount = userResponse.getUserAccount().getBankAccount() + " / " + userResponse.getUserAccount().getAgency();
                    userBalance = String.valueOf(userResponse.getUserAccount().getBalance());

                    //iniciar a nova Activity e passar dos dados
                    intent = new Intent(MainActivity.this, CurrencyActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putString("userAccount", userAccount);
                    bundle.putString("userBalance", userBalance);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("test_post", t.getMessage());

                }
            });

        }
    }


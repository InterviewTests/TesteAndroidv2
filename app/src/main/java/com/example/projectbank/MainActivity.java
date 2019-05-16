package com.example.projectbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectbank.Controller.UserController;
import com.example.projectbank.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    User objUser;
    UserController userController;

    EditText getEtMainEmail;
    EditText getEtPassword;
    Button getBtnLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        objUser = new User();
        final String username = getFromSharedPreferences("username");


        getEtMainEmail = (EditText) findViewById(R.id.etMainEmail);
        getEtPassword = (EditText) findViewById(R.id.etMainPassword);
        getBtnLogin = (Button) findViewById(R.id.btnLogin);

        if(username != "" || username != null)
        {
            getEtMainEmail.setText(username.toString());
        }

        getBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String cpfOrEmail = getEtMainEmail.getText().toString();
                String password = getEtPassword.getText().toString();

                validate(cpfOrEmail, password);

            }
        });
    }

    private void validate(final String cpfOrEmail, String password) {

        userController = new UserController(cpfOrEmail, password);

        if(userController.validaCPF(cpfOrEmail) || userController.validateEmailFormat(cpfOrEmail))
        {
            if(userController.validaSenha(password))
            {
                new UserController(cpfOrEmail, password)
                        .getUserService()
                        .buscarUser(cpfOrEmail,password)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User user = response.body();

                                saveLoginSharedPreferences(cpfOrEmail);

                                Intent intent = new Intent(MainActivity.this, StatementsActivity.class);
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.e("UserService   ", "Erro ao buscar o user:" + t.getMessage());
                            }
                        });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Senha inválida", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Cpf ou Email inválido", Toast.LENGTH_LONG).show();
        }
    }

    public void saveLoginSharedPreferences(String username){
        SharedPreferences sharedPreferences = getSharedPreferences("login_preferences" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }
    public String getFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}

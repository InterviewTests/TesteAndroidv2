package com.example.santandertestebank.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.service.ApiService;
import com.example.santandertestebank.ui.BankPaymentsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar ().hide ();

        loadUI ();
        buttonLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                sendUserInfo ();
            }
        });
    }

    public void sendUserInfo() {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl (ApiService.BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        ApiService service = retrofit.create (ApiService.class);
        final Call<ObjectsLogin> requestLogin = service.bringUser ("tesetei2", "Test@1");

        requestLogin.enqueue (new Callback<ObjectsLogin> () {
            @Override
            public void onResponse(Call<ObjectsLogin> call, Response<ObjectsLogin> response) {

                if (!response.isSuccessful ()) {
                    Toast.makeText (getApplicationContext (), "Erro: " + response.code (),
                            Toast.LENGTH_LONG).show ();
                } else {

                    ObjectsLogin login = response.body ();
                    login.getUserAccountLogin ().getUserId ();

                    Intent i = new Intent (getApplicationContext (), BankPaymentsActivity.class);
                    i.putExtra ("keyLogin", login.getUserAccountLogin ());
                    startActivity (i);
                    finish ();
                }
            }

            @Override
            public void onFailure(Call<ObjectsLogin> call, Throwable t) {
                Toast.makeText (getApplicationContext (), "Erro: " + t, Toast.LENGTH_LONG).show ();

            }
        });
    }

    private void loadUI() {
        editTextUser = findViewById (R.id.edit_text_user);
        editTextPassword = findViewById (R.id.edit_text_password);
        buttonLogin = findViewById (R.id.button_login);
    }
}
package com.example.santandertestebank.model.repository;

import android.content.Intent;
import android.widget.Toast;

import com.example.santandertestebank.model.models.ObjectsLogin;
import com.example.santandertestebank.model.service.ApiService;
import com.example.santandertestebank.ui.BankPaymentsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {


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

}

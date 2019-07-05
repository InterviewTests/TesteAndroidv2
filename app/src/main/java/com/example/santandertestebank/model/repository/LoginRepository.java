package com.example.santandertestebank.model.repository;

import com.example.santandertestebank.model.service.ApiService;
import com.example.santandertestebank.model.models.ObjectsLogin;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository implements ILoginRepository {

    private Retrofit retrofit;
    private ApiService apiService;

    public Call<ObjectsLogin> loginUser(String username, String password) {
        retrofit = new Retrofit.Builder ()
                .baseUrl (ApiService.BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        apiService = retrofit.create (ApiService.class);
        return apiService.loginUSer (username, password);

    }

}

package com.example.santandertestebank.model.repository;

import com.example.santandertestebank.model.models.ObjectsStatements;
import com.example.santandertestebank.model.service.ApiService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankPaymentsRepository implements IBankPaymentsRepository {

    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    public Call<ObjectsStatements> bringStatements(long id) {
        retrofit = new Retrofit.Builder ()
                .baseUrl (ApiService.BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        apiService = retrofit.create (ApiService.class);
        return apiService.bringStatements (id);
    }
}

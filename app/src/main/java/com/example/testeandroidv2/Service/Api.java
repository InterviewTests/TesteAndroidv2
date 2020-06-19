package com.example.testeandroidv2.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Api {

    public static Retrofit getRetrofitInstance(String path){
        return new Retrofit.Builder().baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

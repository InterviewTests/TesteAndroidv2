package com.testeandroidv2.Service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Api {

    public static Retrofit getRetrofitInstance(String path){
        return new Retrofit.Builder().baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

package com.br.example.fakebank.infrastructure.retrofit;

import com.br.example.fakebank.infrastructure.retrofit.endPoints.MainEndPoint;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private String baseUrl = "https://bank-app-test.herokuapp.com/api/";

    private OkHttpClient getNewHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    private Retrofit getNewRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getNewHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MainEndPoint getMainEndPoint(){
        return getNewRetrofit().create(MainEndPoint.class);
    }
}

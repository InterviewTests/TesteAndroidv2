package com.br.example.fakebank.infrastructure.retrofit;

import com.br.example.fakebank.infrastructure.retrofit.endPoints.StatementEndPoint;
import com.br.example.fakebank.infrastructure.retrofit.endPoints.LoginEndPoint;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private OkHttpClient getNewHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    private Retrofit getNewRetrofit(){
        String baseUrl = "https://bank-app-test.herokuapp.com/api/";
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getNewHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LoginEndPoint getMainEndPoint(){
        return getNewRetrofit().create(LoginEndPoint.class);
    }

    public StatementEndPoint getCurrencyEndPoint(){
        return getNewRetrofit().create(StatementEndPoint.class);
    }
}

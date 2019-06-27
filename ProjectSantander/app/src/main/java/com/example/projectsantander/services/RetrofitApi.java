package com.example.projectsantander.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitApi {

    public final Retrofit retrofit;

    public RetrofitApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ApiService getService(){
        return retrofit.create(ApiService.class);
    }
}

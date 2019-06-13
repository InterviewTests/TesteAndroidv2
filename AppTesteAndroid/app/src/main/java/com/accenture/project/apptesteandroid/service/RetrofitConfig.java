package com.accenture.project.apptesteandroid.service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    public static Retrofit getConfig() {
        return new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
}

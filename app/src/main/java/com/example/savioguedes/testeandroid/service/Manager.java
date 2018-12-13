package com.example.savioguedes.testeandroid.service;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Manager {

    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit serviceGenerator(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}

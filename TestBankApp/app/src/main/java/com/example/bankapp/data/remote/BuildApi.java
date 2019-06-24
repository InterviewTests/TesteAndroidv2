package com.example.bankapp.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildApi {
    private Retrofit retrofit;

    public BuildApi(){
        this.retrofit = new Retrofit.Builder().baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


    public  <T> T getBuild(Class<T> type){
        return this.retrofit.create(type);
    }
}

package com.dev.appbank.util;

import com.dev.appbank.service.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(String url) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitService getServiceRetrofit() {
        return this.retrofit.create(RetrofitService.class);
    }

}

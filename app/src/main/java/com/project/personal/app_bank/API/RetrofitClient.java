package com.project.personal.app_bank.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String baseUrl = "https://bank-app-test.herokuapp.com/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {

        if(retrofit==null){

            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

    public static APIInterface getListService(){
        return getInstance().create(APIInterface.class);
    }
}

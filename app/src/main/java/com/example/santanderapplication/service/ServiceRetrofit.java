package com.example.santanderapplication.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRetrofit {
private final static String BASE_API_URL = "https://bank-app-test.herokuapp.com/api/";
private static Retrofit retrofit = null;
private static Gson gson = new GsonBuilder().create();
private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(  )
        .setLevel(HttpLoggingInterceptor.Level.BODY);

private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
        .addInterceptor( httpLoggingInterceptor );
private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

public static <T> T createService (Class <T> tClass) {
    if (retrofit == null) {
        retrofit = new Retrofit.Builder().baseUrl( BASE_API_URL )
                .addConverterFactory( GsonConverterFactory.create(gson) ).build();
    }

    return retrofit.create( tClass );
}
}

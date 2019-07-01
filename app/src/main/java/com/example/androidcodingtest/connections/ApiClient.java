package com.example.androidcodingtest.connections;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BASE_URL = "https://bank-app-test.herokuapp.com/api/";
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor()).build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addInterceptor(new AuthInterceptor())
            .client(okHttpClient)
            .build();

    public static <S> S create(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}

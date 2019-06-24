package com.resource.bankapplication.config;

import com.resource.bankapplication.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static RetrofitFactory instance = null;
    private final Retrofit retrofit;

    public RetrofitFactory() {

        retrofit = new Retrofit.Builder().baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build();
    }

    public static RetrofitFactory getInstance(){
        if(instance == null)
            instance = new RetrofitFactory();
        return instance;
    }

    public <S> S createService(Class<S> serviceClass){return retrofit.create(serviceClass);}

    private OkHttpClient initClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(getLogInterceptor())    //Logging
                .build();
    }

    private static HttpLoggingInterceptor getLogInterceptor() {
        //Logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}

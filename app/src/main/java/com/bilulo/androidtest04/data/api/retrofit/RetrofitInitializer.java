package com.bilulo.androidtest04.data.api.retrofit;

import com.bilulo.androidtest04.data.api.login.LoginApi;
import com.bilulo.androidtest04.data.api.statements.StatementsApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private static volatile RetrofitInitializer instance;
    private Retrofit retrofit;

    private RetrofitInitializer(){
        if (instance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static RetrofitInitializer getInstance() {
        if (instance == null) {
            synchronized (RetrofitInitializer.class) {
                if (instance == null) instance = new RetrofitInitializer();
            }
        }
        return instance;
    }

    public LoginApi getLoginApi() {
        if (retrofit == null)
            getRetrofitInstance();
        return retrofit.create(LoginApi.class);
    }

    public StatementsApi getStatementsApi() {
        if (retrofit == null)
            getRetrofitInstance();
        return retrofit.create(StatementsApi.class);
    }

    private void getRetrofitInstance() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);
        OkHttpClient httpClient = httpClientBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

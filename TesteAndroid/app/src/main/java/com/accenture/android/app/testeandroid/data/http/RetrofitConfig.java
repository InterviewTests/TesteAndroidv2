package com.accenture.android.app.testeandroid.data.http;

import com.accenture.android.app.testeandroid.data.http.resources.StatementResource;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public StatementResource getStatementResource() throws NullPointerException {
        return this.retrofit.create(StatementResource.class);
    }
}
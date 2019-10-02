package com.riso.zup.bank.network;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;

public class OkHttp {
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor()).build();
}

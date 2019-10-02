package com.riso.zup.bank.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor implements okhttp3.Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url();
        Request.Builder requestBuilder = request.newBuilder()
                .url(url);
        request = requestBuilder.build();

        return chain.proceed(request);
    }
}

package com.ivan.bankapp.api;

import androidx.annotation.Nullable;

import okhttp3.ResponseBody;

public final class Response<T> {

    private okhttp3.Response rawResponse;
    private @Nullable T body;
    private @Nullable
    ResponseBody errorBody;

    public @Nullable T body() {
        return body;
    }

    public @Nullable ResponseBody errorBody() {
        return errorBody;
    }
}
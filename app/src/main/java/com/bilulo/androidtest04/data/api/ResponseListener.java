package com.bilulo.androidtest04.data.api;

public interface ResponseListener<T> {
    void onResponseSuccess(T response);

    void onResponseError();
}

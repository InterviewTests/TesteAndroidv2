package com.example.santandertestebank.model.infra;

public interface BaseCallback<T> {

    void onSuccessful(T value);

    void onUnsuccessful(String error);
}

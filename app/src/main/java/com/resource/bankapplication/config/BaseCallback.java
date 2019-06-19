package com.resource.bankapplication.config;

public interface BaseCallback<T> {
    void onSuccessful(T value);
    void onUnsuccessful(String error);
}

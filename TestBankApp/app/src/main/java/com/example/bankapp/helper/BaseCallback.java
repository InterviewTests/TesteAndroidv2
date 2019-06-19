package com.example.bankapp.helper;

public interface BaseCallback<T> {

    void onSuccessful(T value);

    void onUnsuccessful(String text);
}

package com.example.testesantander.infra;

public interface BaseCallback <T>{
    void onSuccessful(T value);
    void onUnsucessful (String error);
}

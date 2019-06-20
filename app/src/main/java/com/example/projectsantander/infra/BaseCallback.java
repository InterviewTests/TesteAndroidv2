package com.example.projectsantander.infra;

public interface BaseCallback <T>{

    void onSuccessful(T value);

    void onUnsuccessful(String error);
}


package com.example.santanderapp.santander.homeScreen.presenter;

public interface ILoginPresenter {
    String verifyHasSavedLogin();
    void callAPI(String login, String senha);
}

package com.example.testesantander;

import Models.UserAccount;

public interface LoginActivityInput{
    void injetarDependencia(String message);
    void iniciarDetailActivity(UserAccount userAccount);
    void startLoadingBar();
    void stopLoadingBar();
}

package com.example.testesantander;

import org.json.JSONObject;

import Domain.UserAccount;

public interface LoginActivityInput{
    void injetarDependencia(String message);
    void iniciarDetailActivity(UserAccount userAccount);
}

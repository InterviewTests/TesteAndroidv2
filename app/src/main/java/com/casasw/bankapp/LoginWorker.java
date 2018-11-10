package com.casasw.bankapp;

interface LoginWorkerInput {
    public String getLoginData();
}

public class LoginWorker implements LoginWorkerInput {

    @Override
    public String getLoginData() {

        String loginData = "dummydata";
        return loginData;
    }
}

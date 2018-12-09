package com.example.rossinyamaral.bank.loginScreen;

import com.example.rossinyamaral.bank.model.UserAccountModel;

interface LoginWorkerInput {
    //Define needed interfaces
    UserAccountModel getUserAccount();
}

public class LoginWorker implements LoginWorkerInput {

    @Override
    public UserAccountModel getUserAccount() {
        return null;
    }
}

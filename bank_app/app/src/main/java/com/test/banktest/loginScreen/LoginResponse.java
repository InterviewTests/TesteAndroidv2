package com.test.banktest.loginScreen;

import com.test.banktest.model.UserModel;
import com.test.banktest.worker.serviceWorker.ServiceError;

public class LoginResponse {

    public String user;
    boolean isUserValid;
    boolean isPasswordValid;

    public UserModel userAccount;
    public ServiceError error;
}

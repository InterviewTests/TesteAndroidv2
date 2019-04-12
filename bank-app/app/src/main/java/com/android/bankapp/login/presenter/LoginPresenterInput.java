package com.android.bankapp.login.presenter;

import com.android.bankapp.login.model.LoginRequest;

public interface LoginPresenterInput {
    void doLogin(LoginRequest request);
}

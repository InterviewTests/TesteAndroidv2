package com.hkdevelopment.bankapp.login.presenter;

public interface LoginPresenterInt {

    String encodeFields(String msg);

    void verifyData(String user, String password);

    void doLogin(String user, String password);
}

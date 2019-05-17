package com.testeandroidv2.contract.view;

public interface LoginView {

    void showProgress();
    void dismissProgress();
    void showMessageInvalidCPF();
    void showMessageInvalidEmail();
    void showLoggedInInterface();
    void showErrorActivity();
}

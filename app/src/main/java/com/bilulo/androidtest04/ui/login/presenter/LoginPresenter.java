package com.bilulo.androidtest04.ui.login.presenter;

import com.bilulo.androidtest04.ui.login.contract.LoginContract;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.PresenterContract {
    public WeakReference<LoginContract.ActivityContract> activity;
}

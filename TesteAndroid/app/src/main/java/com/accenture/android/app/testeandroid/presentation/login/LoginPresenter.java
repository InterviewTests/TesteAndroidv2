package com.accenture.android.app.testeandroid.presentation.login;

import android.content.Context;

import androidx.lifecycle.Lifecycle;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
class LoginPresenter implements LoginContract.Presenter {
    private final static String TAG = "CustomApp - " + LoginPresenter.class.getSimpleName();

    private LoginContract.View view;
    private Context context;

    LoginPresenter(Context context, Lifecycle lifecycle, LoginContract.View view) {
        this.context = context;
        this.view = view;
        lifecycle.addObserver(this);
    }
}

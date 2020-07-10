package com.accenture.android.app.testeandroid.presentation.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.accenture.android.app.testeandroid.data.http.providers.UserAccountProvider;
import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;
import com.accenture.android.app.testeandroid.domain.UserAccount;
import com.accenture.android.app.testeandroid.utils.AuthManager;

import java.util.Locale;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
class LoginPresenter implements LoginContract.Presenter {
    private final static String TAG = "CustomLog - " + LoginPresenter.class.getSimpleName();

    private LoginContract.View view;
    private Context context;

    private AuthManager authManager;

    private UserAccountProvider userAccountProvider;
    private UserAccountProvider.ExpectedResponseLogin userAccountCallback;

    LoginPresenter(Context context, Lifecycle lifecycle, LoginContract.View view) {
        this.context = context;
        this.view = view;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        this.initEvents();

        this.initAuthManager();
        this.initProviders();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        this.buscarUsuarioLogadoRecentemente();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        this.userAccountProvider.finish();
    }

    private void initEvents() {
        this.userAccountCallback = new UserAccountProvider.ExpectedResponseLogin() {
            @Override
            public void onSuccess(String message, UserAccount userAccount) {
                view.unsetLoading();
                view.setContent();

                authManager.setUserAccount(userAccount);

                view.navigateToMainActivity();
            }

            @Override
            public void onFailure(ErrorResponse error) {
                view.unsetLoading();
                view.setContent();

                authManager.clearUserAccount();

                view.setFeedback(String.format(Locale.getDefault(), "%d: %s", error.getStatusCode(), error.getMessage()));
            }
        };
    }

    private void initAuthManager() {
        this.authManager = new AuthManager(this.context);
    }

    private void initProviders() {
        String url = "https://bank-app-test.herokuapp.com/api/";

        this.userAccountProvider = new UserAccountProvider(url);
    }

    @Override
    public void efetuarLogin(String user, String password) {
        this.view.unsetContent();
        this.view.setLoading();

        this.userAccountProvider.efetuarLogin(this.userAccountCallback, user, password);
    }

    private void buscarUsuarioLogadoRecentemente() {
        UserAccount userAccount = this.authManager.getUserAccount();

        if (userAccount.getUserId() != null) {
            this.view.setLoginRecente(userAccount.getName());
        }
    }
}

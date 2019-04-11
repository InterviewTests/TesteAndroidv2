package com.android.bankapp.login;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;

public class LoginRouter implements LoginRouterInput {
    public WeakReference<LoginActivity> activity;

    @Override
    public Intent goToNextScreen() {
        return null;
    }
}

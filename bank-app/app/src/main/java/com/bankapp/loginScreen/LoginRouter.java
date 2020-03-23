package com.bankapp.loginScreen;


import android.content.Intent;

import androidx.annotation.NonNull;

import com.bankapp.helper.ConstantsHelper;
import com.bankapp.statementScreen.StatementActivity;

import java.lang.ref.WeakReference;


interface LoginRouterInput {
    public void passDataToNextScene();
    public Intent determineNextScreen();
}

public class LoginRouter implements LoginRouterInput {

    public static String TAG = LoginRouter.class.getSimpleName();

    public WeakReference<LoginActivity> activity;

    public void passDataToNextScene(Intent intent) {
        intent.putExtra(ConstantsHelper.USER_DETAILS, activity.get().userAccount);
    }

    @NonNull
    @Override
    public Intent determineNextScreen() {
        //TODO: Login - Your rules to next screen here
        return new Intent(activity.get(), StatementActivity.class);
    }

    @Override
    public void passDataToNextScene() {
        Intent intent = determineNextScreen();
        passDataToNextScene(intent);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}

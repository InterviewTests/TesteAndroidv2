package com.bankapp.statementScreen;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.bankapp.loginScreen.LoginActivity;

import java.lang.ref.WeakReference;


interface StatementRouterInput {
    public void passDataToNextScene();
    public Intent determineNextScreen();
}

public class StatementRouter implements StatementRouterInput {

    public static String TAG = StatementRouter.class.getSimpleName();
    public WeakReference<StatementActivity> activity;

    @NonNull
    @Override
    public Intent determineNextScreen() {
        //TODO: Login - Your rules to next screen here
        return new Intent(activity.get(), LoginActivity.class);
    }

    @Override
    public void passDataToNextScene() {
        Intent intent = determineNextScreen();
        activity.get().startActivity(intent);
        activity.get().finish();
    }

}

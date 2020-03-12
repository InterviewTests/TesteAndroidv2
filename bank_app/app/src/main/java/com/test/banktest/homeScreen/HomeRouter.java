package com.test.banktest.homeScreen;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.test.banktest.loginScreen.LoginActivity;

import java.lang.ref.WeakReference;


interface HomeRouterInput {
    public Intent navigateToLogin();

    public void passDataToNextScene(Intent intent);
}

public class HomeRouter implements HomeRouterInput {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;


    @Override
    public Intent navigateToLogin() {
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        return intent;
    }

    @Override
    public void passDataToNextScene( Intent intent) {

    }

}

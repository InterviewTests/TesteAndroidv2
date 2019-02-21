package com.avanade.testesantander2.loginScreen;

import android.content.Intent;

import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.homeScreen.HomeActivity;

import java.lang.ref.WeakReference;

interface LoginRouterInput {
    void openHomeScreen(UserAccount userAccount);
}

public class LoginRouter implements LoginRouterInput {

    public static String TAG = LoginRouter.class.getSimpleName();
    public static final String CHAVE = "userAccount";
    public WeakReference<LoginActivity> activity;
    Intent intent;

    @Override
    public void openHomeScreen(UserAccount userAccount) {

        intent = new Intent(activity.get(), HomeActivity.class);

        UserAccount user = userAccount;
        intent.putExtra(CHAVE, userAccount);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        activity.get().startActivity(intent);

        //activity.get().finish();
    }
}


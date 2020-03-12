package com.test.banktest.loginScreen;

import android.content.Intent;
import android.view.View;

import com.test.banktest.homeScreen.HomeActivity;
import com.test.banktest.model.UserModel;

import java.lang.ref.WeakReference;


interface LoginRouterInput {
    public Intent navigateToSomeWhere(int position);
    public void passUserToNextScene(UserModel userModel, Intent intent);
}

public class LoginRouter implements LoginRouterInput {

    public static String TAG = LoginRouter.class.getSimpleName();
    public WeakReference<LoginActivity> activity;


    @Override
    public Intent navigateToSomeWhere(int position) {
        //Based on the position or someother data decide what is the next scene
        Intent intent = new Intent(activity.get(), HomeActivity.class);
        return intent;
    }

    public void passUserToNextScene(UserModel userModel, Intent intent) {
        //Based on the position or someother data decide the data for the next scene
        // LoginModel flight = activity.get().listOfSomething.get(position);
         intent.putExtra("user",userModel);
    }
}

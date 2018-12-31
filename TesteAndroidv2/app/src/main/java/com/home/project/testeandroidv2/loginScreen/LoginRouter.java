package com.home.project.testeandroidv2.loginScreen;

import android.content.Intent;

import com.home.project.testeandroidv2.bankStatemenScreen.BankStatementActivity;

import java.lang.ref.WeakReference;

interface LoginRouterInput{
    void startNextAcitivy();
}
public class LoginRouter implements LoginRouterInput{

    public WeakReference<LoginActivity> activity;

    @Override
    public void startNextAcitivy() {
        Intent intent = new Intent(activity.get(), BankStatementActivity.class);
        intent.putExtra("userData",activity.get().userAccount);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.get().startActivity(intent);
    }

}

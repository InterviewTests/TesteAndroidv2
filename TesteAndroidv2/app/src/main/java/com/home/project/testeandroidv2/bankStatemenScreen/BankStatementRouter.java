package com.home.project.testeandroidv2.bankStatemenScreen;

import android.content.Intent;

import com.home.project.testeandroidv2.loginScreen.LoginActivity;

import java.lang.ref.WeakReference;

interface BankStatementRouterInput {
    void startNextActivity();
}

public class BankStatementRouter implements BankStatementRouterInput {

    public WeakReference<BankStatementActivity> activity;

    /*
    Gerencia as activities que são chamadas pela BankStatementActivity
     */

    @Override
    public void startNextActivity() {
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        activity.get().bankStatementViewModel.removeUserLogin();
        activity.get().startActivity(intent);
        activity.get().finish();

    }
}

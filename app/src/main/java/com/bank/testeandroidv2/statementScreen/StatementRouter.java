package com.bank.testeandroidv2.statementScreen;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.view.View;
import android.widget.AdapterView;

import com.bank.testeandroidv2.loginScreen.LoginActivity;

import java.lang.ref.WeakReference;


interface StatementRouterInput {
    Intent navigateToSomeWhere(int position);
    void goToLoginScene();
}

public class StatementRouter implements StatementRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = StatementRouter.class.getSimpleName();
    public WeakReference<StatementActivity> activity;


    @Override
    public void goToLoginScene() {
        Intent intent = navigateToSomeWhere(0);
        activity.get().startActivity(intent);
    }


    @NonNull
    @Override
    public Intent navigateToSomeWhere(int position) {
        //Based on the position or someother data decide what is the next scene
        Intent intent = new Intent(activity.get(), LoginActivity.class);
        return intent;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

package com.example.testeandroidv2.loginScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;

import com.example.testeandroidv2.statementScreen.StatementActivity;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

interface LoginRouterInput {
    Intent navigateToSomeWhere(int position);
    void passDataToNextScene(int position, Intent intent);
}

public class LoginRouter implements LoginRouterInput, AdapterView.OnItemClickListener {

    WeakReference<LoginActivity> activity;

    @NonNull
    @Override
    public Intent navigateToSomeWhere(int position) {
        return new Intent(activity.get(), StatementActivity.class);
    }

    @Override
    public void passDataToNextScene(int position, Intent intent) {
         UserViewModel user = activity.get().listOfLoginViewModel.get(position);
         intent.putExtra("user",user);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = navigateToSomeWhere(position);
        passDataToNextScene(position, intent);
        safeLoginOnSharedPreferences();
        activity.get().startActivity(intent);
    }

    private void safeLoginOnSharedPreferences(){
        String jsonLogin = new Gson().toJson(activity.get().listOfLoginViewModel.get(0));
        SharedPreferences preferences = activity.get().getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_login",jsonLogin);
        editor.apply();
    }

}

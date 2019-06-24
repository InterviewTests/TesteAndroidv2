package com.bilulo.androidtest04.ui.login.router;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.ui.list.view.ListActivity;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;
import com.bilulo.androidtest04.utils.SharedPreferencesUtil;

import java.lang.ref.WeakReference;

public class LoginRouter implements LoginContract.RouterContract {
    public WeakReference<LoginActivity> activity;

    private void startListActivity(UserAccountModel userAccountModel) {
        Intent intent = new Intent(activity.get(), ListActivity.class);
        intent.putExtra(ListActivity.EXTRA_USER_ACCOUNT, userAccountModel);
        if (Build.VERSION.SDK_INT > 20) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity.get());
            activity.get().startActivity(intent, options.toBundle());
        } else {
            activity.get().startActivity(intent);
        }
        activity.get().finish();
    }

    @Override
    public void loginSuccessful(UserAccountModel userAccountModel, String username) {
        SharedPreferencesUtil.saveString(activity.get(), LoginActivity.KEY_USER, username);
        startListActivity(userAccountModel);
    }
}

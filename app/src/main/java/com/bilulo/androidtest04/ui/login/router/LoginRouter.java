package com.bilulo.androidtest04.ui.login.router;

import android.content.Intent;

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
        activity.get().startActivity(intent);
        activity.get().finish();
    }

    @Override
    public void loginSuccessful(UserAccountModel userAccountModel, String username) {
        SharedPreferencesUtil.saveString(activity.get(), LoginActivity.KEY_USER, username);
        startListActivity(userAccountModel);
    }
}

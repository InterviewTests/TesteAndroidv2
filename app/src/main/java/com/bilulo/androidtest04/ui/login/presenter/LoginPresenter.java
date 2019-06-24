package com.bilulo.androidtest04.ui.login.presenter;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.PresenterContract {
    public WeakReference<LoginContract.ActivityContract> activity;

    @Override
    public void setData(LoginResponse response, boolean isSuccessful) {
        prepareAndSendData(response, isSuccessful);
    }

    private void prepareAndSendData(LoginResponse response, boolean isSuccessful) {
        if (isSuccessful) {
            if (response != null) {
                UserAccountModel userAccountModel = response.getUserAccountModel();
                if (userAccountModel != null && userAccountModel.getUserId() != null)
                    activity.get().loginSuccessful(userAccountModel);
                else
                    activity.get().displayError();
            } else
                activity.get().displayError();
        } else {
            activity.get().displayError();
        }
    }
}

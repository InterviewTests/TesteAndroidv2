package com.bilulo.androidtest04.ui.login.presenter;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.PresenterContract {
    public WeakReference<LoginContract.ActivityContract> activity;

    @Override
    public void setLoginResponse(LoginResponse response, boolean isSucessful) {
        prepareAndSendData(response, isSucessful);
    }

    private void prepareAndSendData(LoginResponse response, boolean isSucessful) {
        if (isSucessful) {
            UserAccountModel userAccountModel = response.getUserAccountModel();
            if (userAccountModel.getUserId() != null)
                activity.get().loginSuccessful(userAccountModel);
            else
                activity.get().loginError();
        } else {
            activity.get().loginError();
        }
    }
}

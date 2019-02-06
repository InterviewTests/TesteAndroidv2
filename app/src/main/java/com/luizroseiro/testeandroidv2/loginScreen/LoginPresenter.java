package com.luizroseiro.testeandroidv2.loginScreen;

import android.os.Bundle;

import com.luizroseiro.testeandroidv2.mainScreen.MainFragment;
import com.luizroseiro.testeandroidv2.models.UserModel;
import com.luizroseiro.testeandroidv2.utils.Utils;

import static com.luizroseiro.testeandroidv2.utils.Statics.BUNDLE_USER_MODEL;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {

    @Override
    public void presentLoginMetaData(LoginResponse response) {

        UserModel userModel = new UserModel();
        userModel.setUserId(response.getUserAccount().getUserId());
        userModel.setName(response.getUserAccount().getName());
        userModel.setAgency(response.getUserAccount().getAgency());
        userModel.setBankAccount(response.getUserAccount().getFormattedBankAccount());
        userModel.setBalance(response.getUserAccount().getBalance());

        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_USER_MODEL, userModel);

        Utils.replaceFragment(new MainFragment(), bundle);

    }

}
package com.luizroseiro.testeandroidv2.loginScreen;

import com.luizroseiro.testeandroidv2.mainScreen.MainFragment;
import com.luizroseiro.testeandroidv2.models.UserModel;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginMetaData(LoginResponse response);
}

public class LoginPresenter implements LoginPresenterInput {

    private WeakReference<MainFragment> output;

    @Override
    public void presentLoginMetaData(LoginResponse response) {

        UserModel userModel = new UserModel();
        userModel.setName(response.getUserAccount().getName());
        userModel.setAgency(response.getUserAccount().getAgency());
        userModel.setBankAccount(response.getUserAccount().getBankAccount());
        userModel.setBalance(response.getUserAccount().getBalance());

        output.get().displayHomeMetaData(userModel);

    }

}

package com.example.bankapp.ui;

import com.example.bankapp.domain.UserDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.userAccount;
import com.example.bankapp.model.userAccountModel;
import com.example.bankapp.repository.LoginRepository;

public class LoginPresenter implements LoginContract.loginPresenter {

    private LoginContract.loginView view;

    public LoginPresenter(LoginContract.loginView view) {
        this.view = view;
    }

    @Override
    public void login(String userName, String password) {
        UserDomain userDomain = new UserDomain(userName,password);
        userDomain.repository = new LoginRepository();

        userDomain.login(new BaseCallback<userAccountModel>() {
            @Override
            public void onSuccessful(userAccountModel value) {
                view.goToHome();
                view.showErrorMessage("FOI");
            }

            @Override
            public void onUnsuccessful(String text) {
            view.showErrorMessage(text);
            }
        });
    }
}

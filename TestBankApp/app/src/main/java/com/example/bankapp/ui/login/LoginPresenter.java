package com.example.bankapp.ui.login;

import com.example.bankapp.domain.UserDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.model.user.UserAccountModel;
import com.example.bankapp.repository.LoginRepository;

public class LoginPresenter implements LoginViewPresenter.loginPresenter {

    private LoginViewPresenter.loginView view;

    public LoginPresenter(LoginViewPresenter.loginView view) {
        this.view = view;
    }

    @Override
    public void login(String userName, String password) {
        UserDomain userDomain = new UserDomain(userName,password);
        userDomain.repository = new LoginRepository();

        try {
            userDomain.login(new BaseCallback<UserAccountModel>() {
                @Override
                public void onSuccessful(UserAccountModel value) {
                    view.goToHome(value.getUserAccount());
                    //view.showErrorMessage("FOI");
                }

                @Override
                public void onUnsuccessful(String text) {
                view.showErrorMessage(text);
                }
            });
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
        }
    }
}

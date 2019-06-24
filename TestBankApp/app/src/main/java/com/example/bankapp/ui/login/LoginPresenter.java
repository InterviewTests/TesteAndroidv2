package com.example.bankapp.ui.login;

import com.example.bankapp.domain.UserDomain;
import com.example.bankapp.helper.BaseCallback;
import com.example.bankapp.data.remote.model.user.UserAccountModel;
import com.example.bankapp.data.repository.LoginRepository;

public class LoginPresenter implements LoginViewPresenter.loginPresenter {

    private LoginViewPresenter.loginView view;

    public LoginPresenter(LoginViewPresenter.loginView view) {
        this.view = view;
    }

    @Override
    public void login(String userName, String password) {
        view.showProgress(true);
        UserDomain userDomain = new UserDomain(userName, password, view.getContext());
        userDomain.repository = new LoginRepository();

        try {
            userDomain.login(new BaseCallback<UserAccountModel>() {
                @Override
                public void onSuccessful(UserAccountModel value) {
                    view.goToHome(value.getUserAccount());
                    view.showProgress(false);
                }

                @Override
                public void onUnsuccessful(String text) {
                    view.showErrorMessage(text);
                    view.showProgress(false);
                }
            });
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
            view.showProgress(false);
        }
    }
}

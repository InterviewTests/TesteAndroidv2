package com.resource.bankapplication.ui.login;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.data.repository.LoginRepository;
import com.resource.bankapplication.domain.UserAccount;

public class BankLoginPresenter implements BankLoginContract.Presenter {

    private BankLoginContract.View view;

    public BankLoginPresenter(BankLoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        view.showProgress(true);
        UserAccount userAccount = new UserAccount(username, password);
        userAccount.repository = new LoginRepository();
        userAccount.login(new BaseCallback<UserAccount>() {
            @Override
            public void onSuccessful(UserAccount value) {
                view.showProgress(false);
                view.navigationToHome(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.showProgress(false);
                view.showError(error);
            }
        });
    }
}

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
        UserAccount userAccount = new UserAccount(username, password);
        userAccount.repository = new LoginRepository();
        userAccount.login(new BaseCallback<UserAccount>() {
            @Override
            public void onSuccessful(UserAccount value) {
                view.navigationToHome(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.showError(error);
            }
        });
    }
}

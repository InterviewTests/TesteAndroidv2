package com.resource.bankapplication.ui.login;

import android.util.Log;

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

    @Override
    public void loadPreference() {
        UserAccount userAccount = new UserAccount();
        userAccount.repository = new LoginRepository();
        userAccount.loadPreference(view.getContext(), new BaseCallback<UserAccount>() {
            @Override
            public void onSuccessful(UserAccount value) {
                view.setPreferences(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                Log.e("SHARED_PREFERENCES ", error);
            }
        });
    }
}

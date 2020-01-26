package com.msbm.bank.accountDetailScreen;

import java.lang.ref.WeakReference;

public enum AccountDetailConfigurator {
    INSTANCE;
    public void configure(AccountDetailActivity accountDetailActivity) {
        AccountDetailPresenter accountDetailPresenter = new AccountDetailPresenter();
        accountDetailPresenter.accountDetailActivity = new WeakReference<>(accountDetailActivity);

        AccountDetailInteractor accountDetailInteractor = new AccountDetailInteractor();
        accountDetailInteractor.accountDetailPresenterInput = accountDetailPresenter;

        if (accountDetailActivity.accountDetailInteractor == null) {
            accountDetailActivity.accountDetailInteractor = accountDetailInteractor;
        }
    }
}

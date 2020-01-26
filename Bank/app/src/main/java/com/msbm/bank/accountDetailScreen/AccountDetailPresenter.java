package com.msbm.bank.accountDetailScreen;

import java.lang.ref.WeakReference;

interface AccountDetailPresenterInput {
}

public class AccountDetailPresenter implements AccountDetailPresenterInput {

    public static String TAG = AccountDetailPresenter.class.getSimpleName();

    public WeakReference<AccountDetailActivityInput> accountDetailActivity;

}

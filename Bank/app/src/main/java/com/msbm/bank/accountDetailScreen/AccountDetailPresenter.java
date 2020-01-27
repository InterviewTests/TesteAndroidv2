package com.msbm.bank.accountDetailScreen;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface AccountDetailPresenterInput {
    void presentStatementData(StatementResponse response);
}

public class AccountDetailPresenter implements AccountDetailPresenterInput {

    public static String TAG = AccountDetailPresenter.class.getSimpleName();

    public WeakReference<AccountDetailActivityInput> accountDetailActivity;

    @Override
    public void presentStatementData(StatementResponse response) {
        AccountDetailViewModel viewModel = new AccountDetailViewModel();
        viewModel.statements = new ArrayList<>();

        if (response.statementList != null) {
            viewModel.statements = response.statementList;
        }

        accountDetailActivity.get().displayStatementData(viewModel);
    }
}



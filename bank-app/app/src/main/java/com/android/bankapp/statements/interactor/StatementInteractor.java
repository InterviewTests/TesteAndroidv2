package com.android.bankapp.statements.interactor;

import com.android.bankapp.statements.presenter.StatementsPresenter;

public class StatementInteractor implements StatementInteractorInput {

    StatementsPresenter output;

    @Override
    public void loadData() {
        output.loadData();
    }
}

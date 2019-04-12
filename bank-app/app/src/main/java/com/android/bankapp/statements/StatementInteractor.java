package com.android.bankapp.statements;

public class StatementInteractor implements StatementInteractorInput {

    StatementsPresenter output;

    @Override
    public void loadData() {
        output.loadData();
    }
}

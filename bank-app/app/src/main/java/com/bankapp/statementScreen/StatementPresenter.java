package com.bankapp.statementScreen;

import com.bankapp.ErrorResponse;

import java.lang.ref.WeakReference;

interface StatementPresenterInput {
    public void presentStatementData(StatementResponse response);
    public void presentErrorStatementData(ErrorResponse error);
}

public class StatementPresenter implements StatementPresenterInput {

    public static String TAG = StatementPresenter.class.getSimpleName();

    public WeakReference<StatementActivityInput> output;

    @Override
    public void presentStatementData(StatementResponse response) {

    }

    @Override
    public void presentErrorStatementData(ErrorResponse error) {

    }
}

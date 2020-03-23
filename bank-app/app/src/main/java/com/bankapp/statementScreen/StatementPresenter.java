package com.bankapp.statementScreen;

import java.lang.ref.WeakReference;

interface StatementPresenterInput {

}

public class StatementPresenter implements StatementPresenterInput {

    public static String TAG = StatementPresenter.class.getSimpleName();

    public WeakReference<StatementActivityInput> output;


}

package com.bankapp.statementScreen;

import android.content.Context;



interface StatementInteractorInput {
    public void getStatements(StatementRequest statementRequest);
    public void logout(Context context);
}

public class StatementInteractor implements StatementInteractorInput {

    public static String TAG = StatementInteractor.class.getSimpleName();
    public StatementPresenterInput output;
    public StatementWorkerInput aStatementWorkerInput;

    public StatementWorkerInput getStatementWorkerInput() {
        if (aStatementWorkerInput == null) return new StatementWorker();
        return aStatementWorkerInput;
    }

    @Override
    public void getStatements(StatementRequest statementRequest) {
      //TODO: getStatements
    }

    @Override
    public void logout(Context context) {
      //TODO: logout

    }
}

package com.bankapp.statementScreen;

import android.content.Context;

import com.bankapp.ErrorResponse;
import com.bankapp.api.RequestListener;


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
        aStatementWorkerInput = getStatementWorkerInput();
        aStatementWorkerInput.getStatements(statementRequest, new RequestListener<StatementResponse>() {
            @Override
            public void onSuccess(StatementResponse response) {
                output.presentStatementData(response);
            }
            @Override
            public void onFailure(StatementResponse response) {
                output.presentErrorStatementData(response.error);
            }
            @Override
            public void onFailure(ErrorResponse error) {
                output.presentErrorStatementData(error);
            }
        });
    }

    @Override
    public void logout(Context context) {
        aStatementWorkerInput = getStatementWorkerInput();
        aStatementWorkerInput.removeUserFromSharedPreferences(context);

    }
}

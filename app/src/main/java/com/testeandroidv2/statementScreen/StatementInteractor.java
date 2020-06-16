package com.testeandroidv2.statementScreen;

interface StatementInteractorInput {
    public void fetchLoginData(StatementRequest request);
}


public class StatementInteractor implements StatementInteractorInput {

    public static String TAG = StatementInteractor.class.getSimpleName();
    StatementPresenterInput output;
    private StatementWorkerInput aStatementWorkerInput;

    private StatementWorkerInput getLoginWorkerInput() {
        if (aStatementWorkerInput == null) return new StatementWorker();
        return aStatementWorkerInput;
    }

    void setLoginWorkerInput(StatementWorkerInput aStatementWorkerInput) {
        this.aStatementWorkerInput = aStatementWorkerInput;
    }

    @Override
    public void fetchLoginData(StatementRequest request) {
        aStatementWorkerInput = getLoginWorkerInput();
        StatementResponse StatementResponse = new StatementResponse();

        aStatementWorkerInput.getAuthentication();

        output.presentLoginData(StatementResponse);
    }
}

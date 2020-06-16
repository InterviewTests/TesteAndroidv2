package com.testeandroidv2.statementScreen;

interface StatementWorkerInput {
    //Define needed interfaces
    public StatementModel getAuthentication();
}

public class StatementWorker implements StatementWorkerInput {

    @Override
    public StatementModel getAuthentication() {
        return new StatementModel(1, "Teste", 1234, "56789", 3.3332);
    }
}

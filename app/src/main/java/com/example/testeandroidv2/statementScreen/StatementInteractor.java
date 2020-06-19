package com.example.testeandroidv2.statementScreen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

interface StatementInteractorInput {
    void fetchStatementData(StatementRequest request);
    void fetchClientData(ClientRequest clientRequest);
}

public class StatementInteractor implements StatementInteractorInput {

    StatementPresenterInput output;
    private StatementWorkerInput aStatementWorkerInput;

    Callback<JsonObject> callback = new Callback<JsonObject>() {
        StatementResponse statementResponse;
        @Override
        public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
            statementResponse = new Gson().fromJson(response.body(), StatementResponse.class);
            response(statementResponse);
        }

        @Override
        public void onFailure(Throwable t) {
            statementResponse.error = t;
            response(statementResponse);
        }
    };

    private StatementWorkerInput getStatementWorkerInput() {
        if (aStatementWorkerInput == null) return new StatementWorker();
        return aStatementWorkerInput;
    }

    void setStatementWorkerInput(StatementWorkerInput aStatementWorkerInput) {
        this.aStatementWorkerInput = aStatementWorkerInput;
    }

    @Override
    public void fetchStatementData(StatementRequest request) {
        StatementResponse statementResponse = new StatementResponse();
        aStatementWorkerInput = getStatementWorkerInput();
        if(request.user != null) {
            aStatementWorkerInput.buildRequest(request);
            statementResponse = aStatementWorkerInput.getStatementsResponse(callback);
            if(statementResponse != null && statementResponse.statementList != null && !statementResponse.statementList.isEmpty())
                response(statementResponse);
        }else{
            statementResponse.error = "Erro interno, usuário não logado";
            response(statementResponse);
        }
    }

    @Override
    public void fetchClientData(ClientRequest clientRequest) {
        ClientResponse clientResponse = new ClientResponse();
        if(clientRequest.userModel != null) {
            clientResponse.userModel = clientRequest.userModel;
        }else{
            clientResponse.error = "Erro interno, usuário não logado";
        }
        output.presentClientData(clientResponse);
    }

    private void response(StatementResponse statementResponse){
        output.presentStatementData(statementResponse);
    }
}

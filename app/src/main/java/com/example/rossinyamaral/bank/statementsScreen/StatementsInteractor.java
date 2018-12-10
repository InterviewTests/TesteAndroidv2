package com.example.rossinyamaral.bank.statementsScreen;

import android.util.Log;

import com.example.rossinyamaral.bank.BankApi;
import com.example.rossinyamaral.bank.BankApplication;
import com.example.rossinyamaral.bank.model.StatementModel;

import java.util.List;

interface StatementsInteractorInput {
    public void fetchStatementsData(StatementsRequest request);
}


public class StatementsInteractor implements StatementsInteractorInput {

    public static String TAG = StatementsInteractor.class.getSimpleName();
    public StatementsPresenterInput output;
    public StatementsWorkerInput aStatementsWorkerInput;

    public StatementsWorkerInput getStatementsWorkerInput() {
        if (aStatementsWorkerInput == null) return new StatementsWorker();
        return aStatementsWorkerInput;
    }

    public void setStatementsWorkerInput(StatementsWorkerInput aStatementsWorkerInput) {
        this.aStatementsWorkerInput = aStatementsWorkerInput;
    }

    @Override
    public void fetchStatementsData(StatementsRequest request) {
        aStatementsWorkerInput = getStatementsWorkerInput();

        BankApplication.getInstance().bankApi.getStatements(request.userId,
                new BankApi.ApiCallback<List<StatementModel>>() {
                    @Override
                    public void onSuccess(List<StatementModel> object) {

                        StatementsResponse StatementsResponse = new StatementsResponse();
                        StatementsResponse.statements = object;
                        // Call the workers

                        output.presentStatementsData(StatementsResponse);
                    }

                    @Override
                    public void onError(BankApi.ErrorResponse message) {

                    }
                });
    }
}

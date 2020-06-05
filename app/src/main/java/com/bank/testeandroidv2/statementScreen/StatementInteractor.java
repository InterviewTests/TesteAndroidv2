package com.bank.testeandroidv2.statementScreen;

import android.util.Log;

import com.bank.testeandroidv2.ApiEndPoints;
import com.bank.testeandroidv2.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementInteractorInput {
    void loadStatementHeaderData(StatementHeaderRequest request);
    void fetchStatementData(StatementRequest request);
}


public class StatementInteractor implements StatementInteractorInput {

    public static String TAG = StatementInteractor.class.getSimpleName();
    public StatementPresenterInput output;
    public StatementWorkerInput aStatementWorkerInput;

    public StatementWorkerInput getStatementWorkerInput() {
        if (aStatementWorkerInput == null) return new StatementWorker();
        return aStatementWorkerInput;
    }

    public void setStatementWorkerInput(StatementWorkerInput aStatementWorkerInput) {
        this.aStatementWorkerInput = aStatementWorkerInput;
    }

    @Override
    public void loadStatementHeaderData(StatementHeaderRequest request) {
        StatementHeaderResponse statementHeaderResponse = new StatementHeaderResponse();
        statementHeaderResponse.userId = request.userId;
        statementHeaderResponse.name = request.name;
        statementHeaderResponse.agency = request.agency;
        statementHeaderResponse.bankAccount = request.bankAccount;
        statementHeaderResponse.balance = request.balance;
        output.presentStatementHeaderData(statementHeaderResponse);
    }

    @Override
    public void fetchStatementData(StatementRequest request) {
        StatementHeaderResponse statementHeaderResponse = new StatementHeaderResponse();
        statementHeaderResponse.userId = request.userId;
        String userId = String.valueOf(statementHeaderResponse.userId);
//        aStatementWorkerInput = getStatementWorkerInput();
//        aStatementWorkerInput.setStatementeList(userId);
//        ArrayList<StatementModel> list = aStatementWorkerInput.getStatementeList();
        ApiEndPoints apiService = RetrofitService.getRetrofitInstance().create(ApiEndPoints.class);
        Call<ApiStatementModel> call = apiService.getStatement(userId);
        call.enqueue(new Callback<ApiStatementModel>() {
            @Override
            public void onResponse(Call<ApiStatementModel> call, Response<ApiStatementModel> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse");
                    Log.d(TAG, "call");
                    Log.d(TAG, call.toString());
                    Log.d(TAG, "response");
                    ArrayList<StatementModel> stateList = response.body().getStatementList();
                    Log.d(TAG, String.valueOf(stateList));
                    if (null != stateList) {
                        if (!stateList.isEmpty()) {
                            StatementResponse statementResponse = new StatementResponse();
                            statementResponse.list = stateList;
                            output.processRequestFetchStatement(statementResponse);
                        } else
                            output.processErrorRequest("Empty list");
                    } else output.processErrorRequest(null);
                } else
                    output.processErrorRequest(null);
            }

            @Override
            public void onFailure(Call<ApiStatementModel> call, Throwable t) {
                output.processErrorRequest(null);
            }
        });
    }
}

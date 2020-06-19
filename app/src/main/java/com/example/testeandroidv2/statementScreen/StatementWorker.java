package com.example.testeandroidv2.statementScreen;

import com.example.testeandroidv2.Service.Api;
import com.example.testeandroidv2.Service.StatementService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

interface StatementWorkerInput {
    void buildRequest(StatementRequest request);
    StatementResponse getStatementsResponse(Callback<JsonObject> callback);
}

public class StatementWorker implements StatementWorkerInput {

    private Call<JsonObject> call;

    @Override
    public void buildRequest(StatementRequest request) {
        Retrofit api = Api.getRetrofitInstance("https://bank-app-test.herokuapp.com/api/");
        StatementService statementService = api.create(StatementService.class);
        call = statementService.getStatements(request.user.getUserId());
    }

    @Override
    public StatementResponse getStatementsResponse(Callback<JsonObject> callback) {
        try {
            if(callback == null){
                return new Gson().fromJson(call.execute().body(), StatementResponse.class);
            }else {
                call.enqueue(callback);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

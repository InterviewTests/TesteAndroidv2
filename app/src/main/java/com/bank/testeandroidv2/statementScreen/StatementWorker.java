package com.bank.testeandroidv2.statementScreen;

import android.util.Log;

import com.bank.testeandroidv2.ApiEndPoints;
import com.bank.testeandroidv2.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bank.testeandroidv2.statementScreen.StatementInteractor.TAG;

interface StatementWorkerInput {
    void setStatementeList(String userId);
    ArrayList<StatementModel> getStatementeList();

}

public class StatementWorker implements StatementWorkerInput {

    private ArrayList<StatementModel> statementModelList = new ArrayList<>();

    @Override
    public void setStatementeList(String userId) {
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
                    statementModelList = stateList;
                }
            }

            @Override
            public void onFailure(Call<ApiStatementModel> call, Throwable t) {
                Log.d(TAG, "onFailure");
                Log.d(TAG, "call");
                Log.d(TAG, call.toString());
                Log.d(TAG, "t");
                Log.d(TAG, t.toString());
            }
        });
    }

    @Override
    public ArrayList<StatementModel> getStatementeList() {
        return statementModelList;
    }
}

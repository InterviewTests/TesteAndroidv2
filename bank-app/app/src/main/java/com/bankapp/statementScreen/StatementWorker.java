package com.bankapp.statementScreen;

import android.content.Context;

import com.bankapp.ErrorResponse;
import com.bankapp.api.ApiModule;
import com.bankapp.api.RequestListener;
import com.bankapp.api.Service;
import com.bankapp.helper.ConstantsHelper;
import com.bankapp.helper.SecurePreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementWorkerInput {
    public void getStatements(StatementRequest statementRequest, RequestListener<StatementResponse> responseListener);
    public void removeUserFromSharedPreferences(Context context);
}

public class StatementWorker implements StatementWorkerInput {

    ApiModule service = Service.createService(ApiModule.class);
    SecurePreferences preferences;

    @Override
    public void getStatements(StatementRequest statementRequest, RequestListener<StatementResponse> responseListener){
        service.getStatemets(statementRequest.userId).enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if(response.isSuccessful()) {
                    responseListener.onSuccess(response.body());
                } else {
                    responseListener.onFailure(response.body());
                }
            }
            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                responseListener.onFailure(new ErrorResponse("Ops! Não conseguimos carregar alguns dados."));
            }
        });
    }
    @Override
    public void removeUserFromSharedPreferences(Context context) {
        preferences = new SecurePreferences(context, ConstantsHelper.BASE_PREFERENCES, ConstantsHelper.SECRETE_KEY, true);
        preferences.removeValue(ConstantsHelper.USER_PREF);
        preferences.removeValue(ConstantsHelper.PASS_PREF);
    }
}

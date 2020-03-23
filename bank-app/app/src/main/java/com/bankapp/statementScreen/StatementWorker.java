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



    @Override
    public void getStatements(StatementRequest statementRequest, RequestListener<StatementResponse> responseListener){

    }
    @Override
    public void removeUserFromSharedPreferences(Context context) {

    }
}

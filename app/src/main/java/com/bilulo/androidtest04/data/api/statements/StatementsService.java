package com.bilulo.androidtest04.data.api.statements;

import android.util.Log;

import com.bilulo.androidtest04.data.api.ResponseListener;
import com.bilulo.androidtest04.data.api.login.LoginService;
import com.bilulo.androidtest04.data.api.retrofit.RetrofitInitializer;
import com.bilulo.androidtest04.data.model.response.StatementsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsService {
    private RetrofitInitializer retrofitInitializer = RetrofitInitializer.getInstance();

    public void getStatements(int userId, final ResponseListener<StatementsResponse> listener) {
        StatementsApi statementsApi = retrofitInitializer.getStatementsApi();
        statementsApi.getStatements(userId).enqueue(new Callback<StatementsResponse>() {
            @Override
            public void onResponse(Call<StatementsResponse> call, Response<StatementsResponse> response) {
                if (response.isSuccessful()) {
                    listener.onResponseSuccess(response.body());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null)
                        Log.e(LoginService.class.getSimpleName(), responseBody.toString());
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<StatementsResponse> call, Throwable t) {
                Log.e(StatementsService.class.getSimpleName(), t.getMessage());
                listener.onResponseError();
            }
        });
    }
}

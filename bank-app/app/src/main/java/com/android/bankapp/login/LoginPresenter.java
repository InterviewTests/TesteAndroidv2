package com.android.bankapp.login;

import android.util.Log;

import com.android.bankapp.service.BankService;
import com.android.bankapp.service.ServiceGenerator;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginPresenterInput {

    private static final String TAG = "LoginPresenter";
    public WeakReference<LoginActivity> output;
    private BankService service;

    public LoginPresenter() {
        service = ServiceGenerator.generateService(BankService.class);
    }

    @Override
    public void doLogin(final LoginRequest request) {

        Call<LoginResponse> call = service.doLogin(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}

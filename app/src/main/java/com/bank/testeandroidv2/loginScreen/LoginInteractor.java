package com.bank.testeandroidv2.loginScreen;

import android.util.Log;

import com.bank.testeandroidv2.ApiEndPoints;
import com.bank.testeandroidv2.RetrofitService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface LoginInteractorInput {

    void validateLoginData(LoginRequest request);
    void requestLoginDataOnServer(LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    public void setLoginWorkerInput(LoginWorkerInput aLoginWorkerInput) {
        this.aLoginWorkerInput = aLoginWorkerInput;
    }

    @Override
    public void validateLoginData(LoginRequest request) {
        Log.e(TAG, "In method fetchLoginData");
        aLoginWorkerInput = getLoginWorkerInput();
        LoginResponse loginResponse = new LoginResponse();
        // Call the workers
        Log.e(TAG, request.toString());
        loginResponse.status = aLoginWorkerInput.verifyTextFieldsAreCorrect(request);
        Log.e(TAG, loginResponse.toString());
        output.presentLoginDataValidationForm(loginResponse);
    }


    @Override
    public void requestLoginDataOnServer(LoginRequest request) {
        Log.e(TAG, "In method requestLoginDataOnServer");
        LoginModel login = new LoginModel();
        login.password = request.password;
        login.user = request.user;
        ApiEndPoints apiService = RetrofitService.getRetrofitInstance().create(ApiEndPoints.class);
        Call<LoginModel> call = apiService.postLogin(login);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.d(TAG,"onResponse");
                Log.d(TAG,"call");
                Log.d(TAG,call.toString());
                Log.d(TAG,"response");
                Log.d(TAG, String.valueOf(response.body()));
                if(null != response.body())
                    output.processRequestLoginDataOnServer(response.body().getUserAccount());
                else
                    output.processErrorRequest(null);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG,"onFailure");
                Log.d(TAG,"call");
                Log.d(TAG,call.toString());
                Log.d(TAG,"t");
                Log.d(TAG,t.toString());
                output.processErrorRequest(t.getMessage());
            }
        });
    }
}

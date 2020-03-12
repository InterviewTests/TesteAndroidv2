package com.test.banktest.worker.serviceWorker;

import com.test.banktest.homeScreen.HomeRequest;
import com.test.banktest.homeScreen.HomeResponse;
import com.test.banktest.loginScreen.LoginRequest;
import com.test.banktest.loginScreen.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceWorker implements ServiceWorkerInput{

    private static BankService bankService;

    public ServiceWorker(){
        if(bankService == null) {
            this.bankService = new Retrofit.Builder()
                    .baseUrl("https://bank-app-test.herokuapp.com/api/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
                    .create(BankService.class);
        }
    }

    @Override
    public void login(LoginRequest loginRequest, Listener<LoginResponse> listener) {
       bankService.login(loginRequest.user, loginRequest.password).enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.isSuccessful()){
                   listener.onSuccess(response.body());
               } else {
                  listener.onFailure(response.body());
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               LoginResponse response = new LoginResponse();
               response.error = new ServiceError();
               response.error.message = t.getMessage();
               listener.onFailure(response);
           }
       });
    }

    @Override
    public void getStatements(HomeRequest request, Listener<HomeResponse> listener) {
        bankService.getStatements(request.user.userId).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(response.body());
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                HomeResponse response = new HomeResponse();
                response.error = new ServiceError();
                response.error.message = t.getMessage();
                listener.onFailure(response);
            }
        });
    }
}

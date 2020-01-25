package com.msbm.bank.loginScreen;

import com.msbm.bank.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

interface LoginInteractorInput {
    void doLogin(LoginRequest loginRequest);
}

public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();

    public LoginPresenterInput loginPresenterInput;

    @Override
    public void doLogin(LoginRequest loginRequest) {

        if (loginRequest.user.getUsername() == null || loginRequest.user.getUsername().length() == 0) {
            // LoginPresenter empty username
        }

        if (loginRequest.user.getPassword() == null || loginRequest.user.getPassword().length() == 0) {
            // LoginPresenter empty password
        }

        if (validateEmail(loginRequest.user.getUsername()) || validateDocument(loginRequest.user.getUsername())) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginApi loginApi = retrofit.create(LoginApi.class);
            Call<LoginResponse> call = loginApi.login(loginRequest.user.getUsername(), loginRequest.user.getPassword());
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    loginPresenterInput.handleLogin(response.body());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    // LoginPresenter invalid credentials
                }
            });
        } else {
            // LoginPresenter invalid email or document
        }
    }

    private boolean validateEmail(String email) {
        return true;
    }

    private boolean validateDocument(String document) {
        return true;
    }
}

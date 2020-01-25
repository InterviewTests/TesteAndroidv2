package com.msbm.bank.loginScreen;

import android.util.Patterns;

import com.msbm.bank.utils.Constants;
import com.msbm.bank.utils.DocumentUtil;
import com.msbm.bank.utils.StringUtil;

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

        boolean isEmail = loginRequest.user.getUsername().contains("@");

        if (loginRequest.user.getUsername() == null || loginRequest.user.getUsername().length() == 0) {
            loginPresenterInput.emptyUsername();
            return;
        }

        if (loginRequest.user.getPassword() == null || loginRequest.user.getPassword().length() == 0) {
            loginPresenterInput.emptyPassword();
            return;
        }

        if (isEmail && !validateEmail(loginRequest.user.getUsername())) {
            loginPresenterInput.invalidUsername();
            return;
        }

        if (!isEmail) {
            if(loginRequest.user.getUsername().matches("[0-9]+") && loginRequest.user.getUsername().length() == 11) {
                if(!DocumentUtil.isValidCPF(loginRequest.user.getUsername())) {
                    loginPresenterInput.invalidUsername();
                    return;
                }
            } else {
                loginPresenterInput.invalidUsername();
                return;
            }
        }

        if (!StringUtil.hasUpperCharacter(loginRequest.user.getPassword())) {
            loginPresenterInput.invalidPassword();
            return;
        }

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
                loginPresenterInput.invalidCredentials();
            }
        });
    }

    private boolean validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    private boolean validateDocument(String document) {
        if(DocumentUtil.isValidCPF(document)) {
            return true;
        }

        return false;
    }
}

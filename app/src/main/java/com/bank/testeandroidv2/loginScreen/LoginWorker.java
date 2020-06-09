package com.bank.testeandroidv2.loginScreen;

import com.bank.testeandroidv2.ApiEndPoints;
import com.bank.testeandroidv2.RetrofitService;
import com.bank.testeandroidv2.util.CPFUtil;
import com.bank.testeandroidv2.util.EmailUtil;
import com.bank.testeandroidv2.util.PasswordUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginWorkerInput {
    int verifyTextFieldsAreCorrect(LoginRequest request);
    void postLogin(LoginRequest request, LoginCallback loginCallback);
}

public class LoginWorker implements LoginWorkerInput {

    public int verifyTextFieldsAreCorrect(LoginRequest request) {
        LoginModel lm = new LoginModel();

        if ((null == request.user || request.user.isEmpty())
                && (null == request.password || request.password.isEmpty())) {
            //User empty and password empty
            lm.status = LoginStatusMessageEnum.EMPTY_FIELDS.getValue();
        } else if (null == request.password || request.password.isEmpty()) {
            //Password empty
            lm.status = LoginStatusMessageEnum.PASS_FIELD_EMPTY.getValue();
        } else if ((null == request.user || request.user.isEmpty())) {
            //User empty and password filled
            lm.status = LoginStatusMessageEnum.USER_FIELD_EMPTY.getValue();
        } else {
            if (request.user.contains(("@"))) {
                if (EmailUtil.isEmailValid(request.user)) {
                    if (PasswordUtil.isPasswordStrong(request.password)) {
                        lm.status = LoginStatusMessageEnum.FIELDS_OK.getValue();
                    } else {
                        lm.status = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();
                    }
                } else {
                    //User(email) invalid
                    lm.status = LoginStatusMessageEnum.INVALID_USER_EMAIL.getValue();
                }
            } else {
                if (CPFUtil.isCPF(request.user)) {
                    if (PasswordUtil.isPasswordStrong(request.password)) {
                        lm.status = LoginStatusMessageEnum.FIELDS_OK.getValue();
                    } else {
                        lm.status = LoginStatusMessageEnum.INVALID_PASSWORD.getValue();
                    }
                } else {
                    //User(CPF) invalid
                    lm.status = LoginStatusMessageEnum.INVALID_USER_CPF.getValue();
                }
            }

        }
        return lm.status;
    }

    public void postLogin(LoginRequest request, LoginCallback loginCallback) {
        LoginModel login = new LoginModel();
        LoginResponse loginResponse = new LoginResponse();
        login.password = request.password;
        login.user = request.user;
        ApiEndPoints apiService = RetrofitService.getRetrofitInstance().create(ApiEndPoints.class);
        Call<LoginModel> call = apiService.postLogin(login);
        call.enqueue(new Callback<LoginModel>(){
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (null != response.body()) {
                    loginResponse.userAccount = response.body().getUserAccount();
                    loginCallback.onResponse(loginResponse);
                }else {
                    loginResponse.error = null;
                    loginCallback.onResponse(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loginResponse.error = t.getMessage();
                loginCallback.onFailure(loginResponse);
            }
        });
    }
}

package com.bankapp.loginScreen;

import android.content.Context;
import android.text.TextUtils;

import com.bankapp.ErrorResponse;
import com.bankapp.api.RequestListener;
import com.bankapp.helper.ValidationHelper;

interface LoginInteractorInput {
    public void doLogin(Context context, LoginRequest request);
    public void getSavedUser(Context context);
}

public class LoginInteractor implements LoginInteractorInput {

    public static String TAG = LoginInteractor.class.getSimpleName();
    public LoginPresenterInput output;
    public LoginWorkerInput aLoginWorkerInput;

    LoginResponse loginResponse;

    public LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    @Override
    public void doLogin(Context context, LoginRequest request) {
        if(checkFieldsFormat(request)){
            aLoginWorkerInput = getLoginWorkerInput();
            aLoginWorkerInput.login(request, new RequestListener<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse response) {
                    loginResponse = response;
                    //Regra de negócio não foi definida. O response sempre traz os objetos error e userAccount.
                    if(loginResponse!= null && loginResponse.userAccount!=null && loginResponse.userAccount.agency!=null){
                        aLoginWorkerInput.setUserFromSharedPreferences(context, request);
                        output.responseLogin(loginResponse);
                    } else {
                        output.responseErrorLogin(loginResponse.error);
                    }
                }
                @Override
                public void onFailure(ErrorResponse error) {
                    output.responseErrorLogin(error);
                }
                @Override
                public void onFailure(LoginResponse response) {
                    output.responseErrorLogin(response.error);
                }
            });
        } else {
            output.responseErrorInvalidFields(loginResponse);
        }
    }

    @Override
    public void getSavedUser(Context context) {
        aLoginWorkerInput = getLoginWorkerInput();
        LoginResponse loginResponse = aLoginWorkerInput.getUserFromSharedPreferences(context);
        if(loginResponse!=null && loginResponse.loginModel!=null){
            loginResponse.loginModel.user = loginResponse.loginModel.user !=null ? loginResponse.loginModel.user : null;
            loginResponse.loginModel.password = loginResponse.loginModel.password !=null ? loginResponse.loginModel.password : null;
        }
        output.responseSavedUser(loginResponse);
    }

    public boolean checkFieldsFormat(LoginRequest request) {
        loginResponse = new LoginResponse();
        boolean validUser = checkUserFormat(request.loginModel.user, loginResponse);
        boolean validPass = checkPasswordFormat(request.loginModel.password, loginResponse);

        return validUser && validPass;
    }

    public boolean checkUserFormat(String user, LoginResponse loginResponse) {
        if(TextUtils.isEmpty(user)) return false;
        if(ValidationHelper.isEmailValid(user) || ValidationHelper.isValidCpf(user)){
            loginResponse.wrongUser = false;
            return true;
        } else {
            loginResponse.wrongUser = true;
        }
        return false;
    }

    public boolean checkPasswordFormat(String password, LoginResponse loginResponse) {
        if(TextUtils.isEmpty(password)) return false;
        if(ValidationHelper.isValidPassword(password)){
            loginResponse.wrongUser = true;
            return true;
        } else {
            loginResponse.wrongPassword = true;
        }
        return false;
    }
}

package com.android.bankapp.login.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.bankapp.login.view.LoginActivity;
import com.android.bankapp.login.model.LoginRequest;
import com.android.bankapp.login.model.LoginResponse;
import com.android.bankapp.service.BankService;
import com.android.bankapp.service.ServiceGenerator;
import com.android.bankapp.util.UserStateUtil;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginPresenterInput {

    private static final String TAG = "LoginPresenter";
    public static final int PASSWORD_INVALID = 401;
    public static final int REQUEST_ERROR = 400;
    public static final int LOGIN_UNAUTHORIZED = 53;
    public static final int FIELDS_REQUIRED = 50;
    public WeakReference<LoginActivity> output;
    private BankService service;

    public LoginPresenter() {
        service = ServiceGenerator.generateService(BankService.class);
    }

    @Override
    public void doLogin(final LoginRequest request) {

        if (isFieldEmpty(request)) {
            output.get().loginError(FIELDS_REQUIRED);
            return;
        }

        if (!isPasswordValid(request.getPassword())) {
            output.get().loginError(PASSWORD_INVALID);
            return;
        }

        Call<LoginResponse> call = service.doLogin(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (loginResponse != null) {
                    if (loginResponse.getUserAccount().getName() != null) {
                        UserStateUtil.setUserAccount(loginResponse.getUserAccount());
                        output.get().loginSuccess();
                    } else if (loginResponse.getError().getMessage() != null) {
                        output.get().loginError(loginResponse.getError().getCode());
                    } else {
                        output.get().loginError(REQUEST_ERROR);
                        Log.e(TAG, String.valueOf(REQUEST_ERROR));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                output.get().loginError(REQUEST_ERROR);
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private boolean isFieldEmpty(LoginRequest request) {
        return request.getPassword() == null && request.getUser() == null;
    }

    private boolean isPasswordValid(String password) {
        boolean upperCaseFlag = false;
        boolean numberFlag = false;
        boolean specialChar;

        for (int i = 0; i < password.length(); i++) {
            char letter = password.charAt(i);

            if (Character.isDigit(letter))
                numberFlag = true;

            if (Character.isUpperCase(letter))
                upperCaseFlag = true;


            if (numberFlag && upperCaseFlag)
                break;
        }

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        specialChar = matcher.find();


        return upperCaseFlag && numberFlag && specialChar;
    }
}

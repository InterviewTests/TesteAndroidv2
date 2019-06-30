package com.example.androidcodingtest.Login;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.androidcodingtest.Connections.ApiClient;
import com.example.androidcodingtest.Connections.LoginClient;
import com.example.androidcodingtest.R;
import com.example.androidcodingtest.helpers.CpfValidator;
import com.example.androidcodingtest.models.Error;
import com.example.androidcodingtest.models.LoginResponse;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginInteractor.Presenter {

    private LoginInteractor.View view;

    public LoginPresenter(LoginInteractor.View view) {
        this.view = view;
    }

    @Override
    public void login(final String user, String password) {

        Boolean validUser = validateUser(user);
        Boolean validPassword = validatePassword(password);

        if(!validUser){
            view.loginError(R.string.invalid_user);
            return;
        }

        if(!validPassword){
            view.loginError(R.string.invalid_password);
            return;
        }

        HashMap loginObject = new HashMap();
        loginObject.put("user", user);
        loginObject.put("password", password);

        LoginClient loginClient = ApiClient.create(LoginClient.class);
        Call<LoginResponse> call = loginClient.login(loginObject);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful() && loginResponse.getError() == null){
                    view.loginSuccess(user);
                }
                else{
                    view.loginError(loginResponse.getError());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.loginError(null);
            }
        });
    }

    private Boolean validateUser(String user) {

        if(TextUtils.isEmpty(user)){
            return false;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            return true;
        }
        else
            if(CpfValidator.isCPF(user)){
                return true;
            }

        return false;
    }

    private Boolean validatePassword(String password) {

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        boolean hasSpecialCharacter = matcher.find();

        pattern = Pattern.compile("[0-9 ]", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(password);
        boolean hasNumber = matcher.find();

        if(!password.equals(password.toLowerCase()) && hasSpecialCharacter && hasNumber){
            return true;
        }

        return false;
    }
}

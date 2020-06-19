package com.example.testeandroidv2.loginScreen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

interface LoginInteractorInput {
    void fetchLoginData(LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput {

    LoginPresenterInput output;
    private LoginWorkerInput aLoginWorkerInput;

    Callback<JsonObject> callback = new Callback<JsonObject>() {
        LoginResponse loginResponse;
        @Override
        public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
            loginResponse = new Gson().fromJson(response.body(), LoginResponse.class);
            response(loginResponse);
        }

        @Override
        public void onFailure(Throwable t) {
            loginResponse.error = t;
            response(loginResponse);
        }
    };

    private LoginWorkerInput getLoginWorkerInput() {
        if (aLoginWorkerInput == null) return new LoginWorker();
        return aLoginWorkerInput;
    }

    void setLoginWorkerInput(LoginWorkerInput aLoginWorkerInput) {
        this.aLoginWorkerInput = aLoginWorkerInput;
    }

    @Override
    public void fetchLoginData(@NotNull LoginRequest request) {
        aLoginWorkerInput = getLoginWorkerInput();
        LoginResponse loginResponse = new LoginResponse();

        if(isCpf(request.user) || isEmail(request.user)) {
            if(isPasswordValid(request.password)) {
                aLoginWorkerInput.buildRequest(request);
                loginResponse = aLoginWorkerInput.getLoginResponse(callback);
                if(loginResponse != null) response(loginResponse);
            }else{
                loginResponse.error = "A senha deve ter uma letra maiuscula, um caracter especial e um caracter alfanumérico";
                response(loginResponse);
            }
        }else{
            loginResponse.error = "Por favor digite um CPF ou E-mail válido";
            response(loginResponse);
        }
    }

    private void response(LoginResponse response){
        output.presentLoginData(response);
    }

    @NotNull
    private Boolean isCpf(String user){
        Pattern pattern = Pattern.compile("(^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$)");
        return pattern.matcher(user).find();
    }

    @NotNull
    private Boolean isEmail(String user){
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
        return pattern.matcher(user).find();
    }

    @NotNull
    private Boolean isPasswordValid(String password){
        Pattern patternUppercase = Pattern.compile("[A-Z]+");
        Pattern patternSpecialChar = Pattern.compile("\\W+");
        Pattern patternAlphanumericChar = Pattern.compile("\\w+");
        return patternUppercase.matcher(password).find() && patternSpecialChar.matcher(password).find() && patternAlphanumericChar.matcher(password).find();
    }
}

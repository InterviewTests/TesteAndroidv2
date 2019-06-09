package com.br.projetotestesantanter.refactor.loginScreen;

import com.br.projetotestesantanter.ValidationUtil;
import com.br.projetotestesantanter.api.Endpoint;
import com.br.projetotestesantanter.api.RetrofitConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginInteractorInput {
    void fetchHomeMetaData(LoginModel.LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput {

    private final String erroPassword = "A senha deve conter pelo menos uma maiuscula e um caracter especial";
    private final String erroLogin = "Usuário invalido , utilize seu CPF ou e-mail para logar";

    public LoginPresenterInput output;

    @Override
    public void fetchHomeMetaData(LoginModel.LoginRequest request) {

        if (request != null && validationRequestLogin(request)) {
            dataLogin(request);
        }

    }

    private boolean validationRequestLogin(LoginModel.LoginRequest request) {

        if (!ValidationUtil.Companion.isCPF(request.login) && !ValidationUtil.Companion.isValidEmail(request.login)) {

            output.displayMessageErro(erroLogin);
            return false;
        }

        if (!ValidationUtil.Companion.validationPassword(request.password)) {

            output.displayMessageErro(erroPassword);
            return false;
        }

        return true;

    }

    private void dataLogin(LoginModel.LoginRequest request) {

        RetrofitConfiguration.Companion.getRetrofitInstance();
        Endpoint endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);

        endpoint.login(request.login, request.password).enqueue(new Callback<LoginModel.LoginResponse>() {
            @Override
            public void onResponse(Call<LoginModel.LoginResponse> call, Response<LoginModel.LoginResponse> response) {
                output.presentLoginMetaData(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel.LoginResponse> call, Throwable t) {
                output.displayMessageErro(t.getMessage());
            }
        });

    }
}

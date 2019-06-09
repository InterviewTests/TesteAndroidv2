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
        output.visibleProgressBar();

        if (!ValidationUtil.Companion.isCPF(request.login) && !ValidationUtil.Companion.isValidEmail(request.login)) {
            output.hideProgressBar();
            output.displayMessageErro(erroLogin);
            return false;
        }

        if (!ValidationUtil.Companion.validationPassword(request.password)) {
            output.hideProgressBar();
            output.displayMessageErro(erroPassword);
            return false;
        }

        return true;

    }

    private void dataLogin(LoginModel.LoginRequest request) {

        RetrofitConfiguration.Companion.getRetrofitInstance();
        Endpoint endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(Endpoint.class);

        endpoint.login(request.login, request.password).enqueue(new Callback<LoginModel.Login>() {
            @Override
            public void onResponse(Call<LoginModel.Login> call, Response<LoginModel.Login> response) {
                output.hideProgressBar();
                output.presentLoginMetaData(response.body());
            }

            @Override
            public void onFailure(Call<LoginModel.Login> call, Throwable t) {
                output.hideProgressBar();
                output.displayMessageErro(t.getMessage());
            }
        });
    }
}

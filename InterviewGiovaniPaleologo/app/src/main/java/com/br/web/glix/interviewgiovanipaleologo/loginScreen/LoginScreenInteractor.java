package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import com.br.web.glix.interviewgiovanipaleologo.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.validarCPF;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.validarEmail;
import static com.br.web.glix.interviewgiovanipaleologo.utils.Utils.validarSenha;

interface LoginScreenInteractorInput {
    void doLogin(LoginScreenRequest loginScreenRequest);
}

public class LoginScreenInteractor implements LoginScreenInteractorInput {
    LoginScreenPresenterInput loginScreenPresenterInput;

    @Override
    public void doLogin(final LoginScreenRequest loginScreenRequest) {

        boolean isEmail = loginScreenRequest.user.getUsername().contains("@");

        if (loginScreenRequest.user.getUsername() == null || loginScreenRequest.user.getUsername().length() == 0) {
            loginScreenPresenterInput.emptyUsername();
            return;
        }

        if (loginScreenRequest.user.getPassword() == null || loginScreenRequest.user.getPassword().length() == 0) {
            loginScreenPresenterInput.emptyPassword();
            return;
        }

        if (isEmail && !validarEmail(loginScreenRequest.user.getUsername())) {
            loginScreenPresenterInput.invalidUsername();
            return;
        }

        if (!isEmail) {
            if(loginScreenRequest.user.getUsername().matches("[0-9]+") && loginScreenRequest.user.getUsername().length() == 11) {
                if(!validarCPF(loginScreenRequest.user.getUsername())) {
                    loginScreenPresenterInput.invalidUsername();
                    return;
                }
            } else {
                loginScreenPresenterInput.invalidUsername();
                return;
            }
        }

        if (!validarSenha(loginScreenRequest.user.getPassword())) {
            loginScreenPresenterInput.invalidPassword();
            return;
        }

        if (!loginScreenPresenterInput.verificarConexao()){
            loginScreenPresenterInput.connectionRefused();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginScreenAPI loginApi = retrofit.create(LoginScreenAPI.class);
        Call<LoginScreenResponse> call = loginApi.login(loginScreenRequest.user.getUsername(), loginScreenRequest.user.getPassword());
        call.enqueue(new Callback<LoginScreenResponse>() {
            @Override
            public void onResponse(Call<LoginScreenResponse> call, Response<LoginScreenResponse> response) {

                if (response.body().userAccount.getUserId() == null){
                    loginScreenPresenterInput.invalidLogin();
                    loginScreenPresenterInput.clearPassword();
                    return;
                }

                loginScreenPresenterInput.presentLoginScreenData(response.body());
                loginScreenPresenterInput.setUserAccountData(response.body().userAccount);
                loginScreenPresenterInput.savePreferences(loginScreenRequest.user.getUsername());
                loginScreenPresenterInput.clearPassword();
            }

            @Override
            public void onFailure(Call<LoginScreenResponse> call, Throwable t) {
                loginScreenPresenterInput.invalidLogin();
                loginScreenPresenterInput.clearPassword();
            }
        });
    }
}

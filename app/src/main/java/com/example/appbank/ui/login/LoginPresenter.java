package com.example.appbank.ui.login;

import com.example.appbank.data.remote.Contract.ILoginEndpoint;
import com.example.appbank.data.remote.ServiceGenerator;
import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;

    public LoginPresenter(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void login(LoginRequest loginRequest) {

        //preparando a minha classe de serviço para fazer uma chamada Rest
        ILoginEndpoint loginService = ServiceGenerator.createService(ILoginEndpoint.class);

        //Criar a chamada do endPoint que eu preciso
        Call<LoginResponse> call = loginService.postLogin(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getError().getCode() != 0) {
                        //Toast.makeText(getApplicationContext(), loginResponse.getError().getMassage(), Toast.LENGTH_LONG).show();
                        view.showError();
                        return;
                    }
                    view.navigateToList(loginResponse);

                    //return;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.showError();

            }
        });
    }

    @Override
    public void validUserData(String userName) {
        if (userName.matches("[0-9]+")) {
            if (userName.length() != 11) {
                view.errorUsername("CPF inválido");
                view.enabledButton(false);
                return;
            }
            view.enabledButton(true);
        } else {
            if (!userName.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")) {

                view.errorUsername("E-mail inválido");
                view.enabledButton(false);
                return;
            }
            view.enabledButton(true);
        }
    }

    @Override
    public boolean validPasswordData(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d](?=.*[!@#$*_%^&+=])(?=\\S+$)(?=\\S+$).{4,}$")) {
            return true;
        }
        view.errorPassword("A senha deve conter 1 carecter especial, uma letra maiúscula" +
                " e um caracter alfanumérico");
        return false;
    }


}


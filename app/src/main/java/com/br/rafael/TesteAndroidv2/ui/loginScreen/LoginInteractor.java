package com.br.rafael.TesteAndroidv2.loginScreen;

import com.br.rafael.TesteAndroidv2.Util.Constants;
import com.br.rafael.TesteAndroidv2.Util.ValidationUtil;
import com.br.rafael.TesteAndroidv2.data.api.LoginApi;
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;
import com.br.rafael.TesteAndroidv2.data.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginInteractorInput {
    void fetchHomeMetaData(LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput{

    public LoginPresenterInput output;


    @Override
    public void fetchHomeMetaData(LoginRequest request) {


        if (request != null && validationRequestLogin(request)) {
            dataLogin(request);
        }
    }

    private void dataLogin(LoginRequest request) {
        output.visibleProgressBar();

        LoginApi endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(LoginApi.class);

        endpoint.userLogin(request.getLogin(),request.getPassword()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                output.hideProgressBar();
                output.presentLoginMetaData(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                output.hideProgressBar();
                output.displayMessageErro(t.getMessage());
            }
        });



    }

    private boolean validationRequestLogin(LoginRequest request){

        if (!ValidationUtil.Companion.isCPF(request.getLogin())
                && !ValidationUtil.Companion.isValidEmail(request.getLogin())) {
            output.hideProgressBar();
            output.displayMessageErro(Constants.INSTANCE.getErroLogin());
            return false;
        }

        if (!ValidationUtil.Companion.validationPassword(request.getPassword())) {
            output.hideProgressBar();
            output.displayMessageErro(Constants.INSTANCE.getErroPassord());
            return false;
        }

        return true;

    }
}

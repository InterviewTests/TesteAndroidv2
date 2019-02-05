package com.luizroseiro.testeandroidv2.loginScreen;

import android.support.annotation.NonNull;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.utils.AppPreferences;
import com.luizroseiro.testeandroidv2.utils.DataService;
import com.luizroseiro.testeandroidv2.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginInteractorInput {
    void loginUser(LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput {

    private LoginPresenterInput output;

    @Override
    public void loginUser(LoginRequest request) {
        DataService.loginUser(request.getUser(), request.getPassword(),
                new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call,
                                   @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AppPreferences.setUserLoggedIn();
                    output.presentLoginMetaData(response.body());
                }
                else
                    Utils.createToast(MainActivity.getContext().getString(R.string.error_login));
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Utils.createToast(MainActivity.getContext().getString(R.string.error_login));
            }
        });
    }

}

package com.accenture.android.app.testeandroid.data.http.providers;

import android.util.Log;

import com.accenture.android.app.testeandroid.data.converters.UserAccountConverter;
import com.accenture.android.app.testeandroid.data.http.helpers.StatusCode;
import com.accenture.android.app.testeandroid.data.http.providers.generics.BaseProvider;
import com.accenture.android.app.testeandroid.data.http.resources.AuthResource;
import com.accenture.android.app.testeandroid.data.http.responses.UserAccountResponse;
import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;
import com.accenture.android.app.testeandroid.domain.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class UserAccountProvider extends BaseProvider {
    private AuthResource resource;

    private Call<UserAccountResponse> callLogin;

    public UserAccountProvider(String baseUrl) {
        super(baseUrl);

        this.resource = this.retrofit.getAuthResource();
    }

    public void efetuarLogin(final ExpectedResponseLogin callback, String user, String password) {
        this.callLogin = this.resource.login(user, password);
        this.callLogin.enqueue(new Callback<UserAccountResponse>() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {
                if (response.code() == StatusCode.StatusCodeEnum.OK.value) {
                    ErrorResponse error = response.body().getError();

                    if (error.getStatusCode() == null) {
                        UserAccount statements = UserAccountConverter.toDomain(response.body().getData());

                        callback.onSuccess("Busca efetuada com sucesso.", statements);
                    } else {
                        callback.onFailure(error);
                    }
                } else {
                    callback.onFailure(new ErrorResponse(response.code(), response.message()));
                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {
                if (call.isCanceled()) {
                    Log.e(TAG, "efetuarLogin: Requisição cancelada.");
                } else {
                    callback.onFailure(new ErrorResponse(0, t.getMessage()));
                }
            }
        });
    }

    @Override
    public void finish() {
        if (this.callLogin != null) {
            this.callLogin.cancel();
        }
    }

    public interface ExpectedResponseLogin {
        void onSuccess(String message, UserAccount userAccount);

        void onFailure(ErrorResponse error);
    }
}

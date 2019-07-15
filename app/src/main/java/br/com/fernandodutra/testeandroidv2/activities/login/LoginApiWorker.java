package br.com.fernandodutra.testeandroidv2.activities.login;

import br.com.fernandodutra.testeandroidv2.api.ClientAPI;
import br.com.fernandodutra.testeandroidv2.api.InterfaceAPI;

import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 10:20
 * TesteAndroidv2_CleanCode
 */
interface LoginApiWorkerInput {
    void getUserAccountResponse(final OnFinished onFinishedListener, LoginRequest loginRequest);
}

public class LoginApiWorker implements LoginApiWorkerInput {

    @Override
    public void getUserAccountResponse(final OnFinished onFinishedListener, LoginRequest loginRequest) {
        InterfaceAPI apiService =
                ClientAPI.getClient().create(InterfaceAPI.class);

        Call<UserAccountWorker> call = apiService.getUserAccount(loginRequest.getLogin());
        call.enqueue(new Callback<UserAccountWorker>() {
            @Override
            public void onResponse(Call<UserAccountWorker> call, Response<UserAccountWorker> response) {
                UserAccountWorker userAccountWorker = response.body();
                onFinishedListener.onFinished(userAccountWorker);
            }

            @Override
            public void onFailure(Call<UserAccountWorker> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}

package com.projeto.testedevandroidsantander.ui.loginScreen;

import com.projeto.santander.api.RetrofitClient;
import com.projeto.santander.model.UsuarioDTO;
import com.projeto.santander.util.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface UsuarioWorkerInput {
    void getUsuarioAccount(LoginRequest loginRequest);
    String getUserSharedPreferences();
}

public class UsuarioWorker implements UsuarioWorkerInput {

    LoginInteractor interactor;

    @Override
    public void getUsuarioAccount(final LoginRequest loginRequest) {
        Call<UsuarioDTO> call = RetrofitClient.getInstance().getUsuarioApi().login(loginRequest.getLogin(), loginRequest.getPassword());

        call.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if(response.isSuccessful()){
                    SharedPrefManager.getInstance(interactor.output.getContext()).saveUser(loginRequest.getLogin());
                    interactor.presentLoginMetaData(response.body());
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
            }
        });
    }

    @Override
    public String getUserSharedPreferences() {
        return SharedPrefManager.getInstance(interactor.output.getContext()).getUser();
    }


}

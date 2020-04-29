package com.br.example.fakebank.domains.services;

import com.br.example.fakebank.domains.repositories.LoginRepository;
import com.br.example.fakebank.infrastructure.retrofit.RetrofitConfig;
import com.br.example.fakebank.infrastructure.retrofit.responses.LoginResponse;
import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService implements LoginRepository {
    private RetrofitConfig retrofitConfig;

    public LoginService() {
        this.retrofitConfig = new RetrofitConfig();
    }

    @Override
    public Observable<LoginResponse> authLogin(String user, String password) {
        return Observable.create(emitter -> {

            Call<LoginResponse> call = retrofitConfig.getMainEndPoint().makeRequestLogin(user,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            LoginResponse loginResponse = response.body();
                            emitter.onNext(loginResponse);
                        }else{
                            LoginResponse loginResponse =  new LoginResponse();
                            ErrorEntity errorEntity = new ErrorEntity();
                            errorEntity.setMessage("Erro ao mapear");
                            loginResponse.setErrorEntity(errorEntity);
                            emitter.onNext(loginResponse);
                        }
                    }else {
                        LoginResponse loginResponse =  new LoginResponse();
                        ErrorEntity errorEntity = new ErrorEntity();
                        errorEntity.setMessage("Usuário não encontrado");
                        loginResponse.setErrorEntity(errorEntity);
                        emitter.onNext(loginResponse);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }
}

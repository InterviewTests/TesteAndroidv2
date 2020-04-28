package com.br.example.fakebank.domains.services;

import com.br.example.fakebank.domains.repositories.MainRepository;
import com.br.example.fakebank.infrastructure.retrofit.RetrofitConfig;
import com.br.example.fakebank.infrastructure.retrofit.responses.MainResponse;
import com.br.example.fakebank.infrastructure.retrofit.responses.entities.ErrorEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService implements MainRepository {
    private RetrofitConfig retrofitConfig;

    public MainService() {
        this.retrofitConfig = new RetrofitConfig();
    }

    @Override
    public Observable<MainResponse> authLogin(String user, String password) {
        return Observable.create(emitter -> {

            Call<MainResponse> call = retrofitConfig.getMainEndPoint().makeRequestLogin(user,password);
            call.enqueue(new Callback<MainResponse>() {
                @Override
                public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            MainResponse mainResponse = response.body();
                            emitter.onNext(mainResponse);
                        }else{
                            MainResponse mainResponse =  new MainResponse();
                            ErrorEntity errorEntity = new ErrorEntity();
                            errorEntity.setMessage("Erro ao mapear");
                            mainResponse.setErrorEntity(errorEntity);
                            emitter.onNext(mainResponse);
                        }
                    }else {
                        MainResponse mainResponse =  new MainResponse();
                        ErrorEntity errorEntity = new ErrorEntity();
                        errorEntity.setMessage("Usuário não encontrado");
                        mainResponse.setErrorEntity(errorEntity);
                        emitter.onNext(mainResponse);
                    }
                }

                @Override
                public void onFailure(Call<MainResponse> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }
}

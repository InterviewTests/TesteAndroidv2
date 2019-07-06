package com.projeto.testedevandroidsantander.ui.mainScreen;

import com.projeto.santander.api.RetrofitClient;
import com.projeto.santander.model.LancamentoDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


interface LancamentoWorkerInput{
    void getLancamentosByIdUser(Integer idUser);
}

public class LancamentoWorker implements LancamentoWorkerInput {

    MainInteractor interactor;

    @Override
    public void getLancamentosByIdUser(Integer idUser) {
        Call<LancamentoDTO> call = RetrofitClient.getInstance().getLancamentoApi().getLancamentosByIdUsuario(idUser);
        call.enqueue(new Callback<LancamentoDTO>() {
            @Override
            public void onResponse(Call<LancamentoDTO> call, Response<LancamentoDTO> response) {
                if(response.isSuccessful()){
                    interactor.presentMainMetaData(response.body());
                }
            }

            @Override
            public void onFailure(Call<LancamentoDTO> call, Throwable t) {

            }
        });
    }
}

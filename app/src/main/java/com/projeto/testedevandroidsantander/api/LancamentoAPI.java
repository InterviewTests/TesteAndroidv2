package com.projeto.testedevandroidsantander.api;

import com.projeto.testedevandroidsantander.model.LancamentoDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LancamentoAPI {
    @GET("statements/{idUser}")
    Call<LancamentoDTO> getLancamentosByIdUsuario(@Path("idUser") Integer idUser);
}

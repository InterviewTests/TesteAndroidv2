package com.santander.vicolmoraes.santander.ViewModel;

import com.santander.vicolmoraes.santander.Model.DataTransacaoVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TransacaoService {

    @GET("statements/{idUser}")
    Call<DataTransacaoVO> buscarTransacoes(@Path("idUser") String usuario);
}



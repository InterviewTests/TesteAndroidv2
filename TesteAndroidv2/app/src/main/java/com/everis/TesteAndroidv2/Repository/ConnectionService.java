package com.everis.TesteAndroidv2.Repository;

import com.everis.TesteAndroidv2.Login.Model.DadosLogin;
import com.everis.TesteAndroidv2.Extrato.Model.Lancamento;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConnectionService {

    @GET("api/statements/{idUser}")
    Call<Lancamento> checarExtrato(@Path("idUser") Integer idUser);

    @FormUrlEncoded
    @POST("api/login")
    Call<DadosLogin> checarLogin(@Field("user") String user, @Field("password") String password);
}

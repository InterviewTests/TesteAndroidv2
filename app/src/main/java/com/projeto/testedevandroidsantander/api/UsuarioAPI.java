package com.projeto.testedevandroidsantander.api;

import com.projeto.testedevandroidsantander.model.UsuarioDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioAPI {

    @FormUrlEncoded
    @POST("login")
    Call<UsuarioDTO> login(@Field("user") String user, @Field("password") String password);
}

package com.santander.vicolmoraes.santander.ViewModel;

import com.santander.vicolmoraes.santander.Model.DataUsuarioVO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioService {

    @FormUrlEncoded
    @POST("login")
    Call<DataUsuarioVO> buscarUsuario(@Field("user") String username,
                                      @Field("password") String password);
}

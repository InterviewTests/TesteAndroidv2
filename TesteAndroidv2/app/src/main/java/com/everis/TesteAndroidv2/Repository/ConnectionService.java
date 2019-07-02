package com.everis.TesteAndroidv2.Repository;

import com.everis.TesteAndroidv2.Login.Model.LoginData;
import com.everis.TesteAndroidv2.Statement.Model.Statement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConnectionService {

    @GET("api/statements/{idUser}")
    Call<Statement> checkStatementGET(@Path("idUser") Integer idUser);

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginData> checkLoginPOST(@Field("user") String user, @Field("password") String password);
}

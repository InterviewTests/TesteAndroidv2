package br.com.santanderteste.network.networkinterface;

import br.com.santanderteste.model.LoginResponse;
import br.com.santanderteste.model.StatementResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitClient {

    @FormUrlEncoded
    @POST("/api/login")
    Observable<LoginResponse> getUser(@Field("user") String user, @Field("password") String password);

    @GET("/api/statements/1")
    Observable<StatementResponse> getStatements();

}

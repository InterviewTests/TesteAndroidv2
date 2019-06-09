package br.com.douglas.fukuhara.bank.network;

import br.com.douglas.fukuhara.bank.network.vo.LoginVo;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    String LOGIN = "/api/login";

    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginVo> doLogin(@Field("user") String user, @Field("password") String password);
}

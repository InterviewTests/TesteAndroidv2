package br.com.douglas.fukuhara.bank.network;

import br.com.douglas.fukuhara.bank.network.vo.LoginVo;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    String LOGIN = "/api/login";
    String STATEMENT_ID_PATH = "statementId";

    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginVo> doLogin(@Field("user") String user, @Field("password") String password);
    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginVo> doLoginv2(@Field("user") String user);
}

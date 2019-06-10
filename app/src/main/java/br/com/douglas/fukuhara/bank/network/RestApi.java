package br.com.douglas.fukuhara.bank.network;

import br.com.douglas.fukuhara.bank.network.vo.LoginVo;
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {

    String LOGIN = "/api/login";
    String STATEMENTS = "/api/statements";
    String STATEMENT_ID_PATH = "statement_id";
    String STATEMENT_ID = "/{" + STATEMENT_ID_PATH + "}";

    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginVo> doLogin(@Field("user") String user, @Field("password") String password);

    @GET(STATEMENTS + STATEMENT_ID)
    Observable<StatementListVo> getUserData(@Path(STATEMENT_ID_PATH) int userId);
}

package br.com.fernandodutra.testeandroidv2.api;

import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;
import br.com.fernandodutra.testeandroidv2.models.UserAccountResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 18/06/2019
 * Time: 16:43
 * TesteAndroidv2
 */
public interface InterfaceAPI {

    @POST("/api/login")
    Call<UserAccountResponse> getUserAccount(@Body Login login);

    @GET
    Call<StatementListResponse> getStatementList(@Url String url);
}

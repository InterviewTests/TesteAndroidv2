package resource.estagio.testesantander.infra;

import resource.estagio.testesantander.login.LoginResponse;
import resource.estagio.testesantander.statement.StatementResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("user") String user,
            @Field("password") String password
    );

    @GET("statements/{id}")
    Call<StatementResponse> getStatementList(@Path("id") long id);


}

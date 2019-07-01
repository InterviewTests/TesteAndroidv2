package resource.estagio.testesantander;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IBankServices {

    @GET("statemensts/1")
    Call<Statements> getStatementsList();

    @POST("login")
    Call<Login> getLoginUser(
            @Field("user") String user,
            @Field("password") String password
    );


}

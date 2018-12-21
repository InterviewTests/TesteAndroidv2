package teste.claudio.com.testsantander;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Body;

public interface RetrofitService {
    //Interface método login
    @FormUrlEncoded
    @POST("login")
    Call<user> login(@Field("user") String user,
                @Field("password") String password);

    //Interface método statements
    @GET("statements/{user}")
    Call<JSONResponse> statements(
        @Path("user") Integer user);

}

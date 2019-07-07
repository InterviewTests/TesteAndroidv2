package ssilvalucas.testeandroidv2.data.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ssilvalucas.testeandroidv2.data.model.LoginResponse;

public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> onLogin(@Field("user") String username, @Field("password") String password);
}

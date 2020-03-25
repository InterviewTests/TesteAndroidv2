package br.com.amilton.retrofit;

import br.com.amilton.model.Login;
import br.com.amilton.model.Statements;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    @POST("login")
    @FormUrlEncoded //usar fieldMap?
    Call<Login> login(@Field("user") String user, @Field("password") String password);

    @GET("statements/{idUser}")
    Call<Statements> statements(@Path("idUser") String idUser);
}

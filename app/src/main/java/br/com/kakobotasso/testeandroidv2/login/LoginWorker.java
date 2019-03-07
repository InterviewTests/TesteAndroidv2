package br.com.kakobotasso.testeandroidv2.login;

import br.com.kakobotasso.testeandroidv2.util.RetrofitConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface LoginWorkerInput {
    void sendLoginInfo(LoginRequest request, Callback<LoginModel> callback);
}

interface LoginWorkerService {
    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginModel> sendLoginInfo(@Field("user") String user,
                                   @Field("password") String password);
}

public class LoginWorker implements LoginWorkerInput {
    @Override
    public void sendLoginInfo(LoginRequest request, Callback<LoginModel> callback) {
        LoginWorkerService service =
                (LoginWorkerService) RetrofitConfiguration.getService(LoginWorkerService.class);
        Call<LoginModel> sendLogin =
                service.sendLoginInfo(request.getUser(), request.getPassword());
        sendLogin.enqueue(callback);
    }
}

package resource.estagio.testesantander.login;

import resource.estagio.testesantander.infra.ApiServices;
import resource.estagio.testesantander.infra.BaseCallback;
import resource.estagio.testesantander.infra.RetrofitClient;
import resource.estagio.testesantander.model.UserContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAuth implements UserContract.IUserRepository{
    @Override
    public void login(String username, String password, final BaseCallback<LoginResponse> onResult) {
        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .createService(ApiServices.class)
                .userLogin(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    LoginResponse loginResponse = response.body();
                   onResult.onSuccessful(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
               onResult.onUnsucessful(t.getMessage());
            }
        });
    }
}

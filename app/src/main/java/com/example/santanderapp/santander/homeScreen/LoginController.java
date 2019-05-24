package com.example.santanderapp.santander.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.santanderapp.santander.R;
import com.example.santanderapp.santander.detailScreen.DetailsActivity;
import com.example.santanderapp.santander.homeScreen.interfaceService.LoginService;
import com.example.santanderapp.santander.homeScreen.model.RequestLogin;
import com.example.santanderapp.santander.homeScreen.model.ResponseLogin;
import com.example.santanderapp.santander.util.Utils;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.santanderapp.santander.homeScreen.LoginActivity.validatesData;

public class LoginController {

    private Context context;

    public LoginController(Context context) {

        this.context = context;
    }

    //Pegando o último usuário salvo, caso não tiver deixa em branco
    public String verifyHasSavedLogin() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.userAccount), MODE_PRIVATE);

        return preferences.getString(context.getString(R.string.user), "");

    }

    public void teste(final String edtUser, String edtPassword) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService service = retrofit.create(LoginService.class);

        RequestLogin requestLogin = new RequestLogin();

        requestLogin.user = edtUser;
        requestLogin.password = edtPassword;
        if (validatesData(edtUser, edtPassword)) {
            Call<ResponseLogin> requestCatalog = service.login(requestLogin);

            requestCatalog.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    //progress.dismiss();
                    EventBus.getDefault().register(context);
                    EventBus.getDefault().post(true);
                    EventBus.getDefault().unregister(context);
                    if (!response.isSuccess()) {
                        Toast.makeText(context, context.getString(R.string.error) + response.code(), Toast.LENGTH_SHORT).show();

                    } else {
                        ResponseLogin dateClient = response.body();

                        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.userAccount), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(context.getString(R.string.userId), dateClient.userAccount.userId);
                        editor.putString(context.getString(R.string.name), dateClient.userAccount.name);
                        editor.putString(context.getString(R.string.bankAccount), dateClient.userAccount.bankAccount);
                        editor.putString(context.getString(R.string.agency), dateClient.userAccount.agency);
                        editor.putFloat(context.getString(R.string.balance), dateClient.userAccount.balance);

                        editor.putString(context.getString(R.string.user), edtUser);

                        editor.apply();
                        Intent intent = new Intent(context, DetailsActivity.class);
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    if (!Utils.isConected(context)) {
                        Toast.makeText(context, context.getString(R.string.notConnectInternet), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(context, context.getString(R.string.fail) + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            //progress.dismiss();
            EventBus.getDefault().register(context);
            EventBus.getDefault().post(true);
            EventBus.getDefault().unregister(context);
            Toast.makeText(context, context.getString(R.string.errorLogin), Toast.LENGTH_SHORT).show();
        }

    }

}

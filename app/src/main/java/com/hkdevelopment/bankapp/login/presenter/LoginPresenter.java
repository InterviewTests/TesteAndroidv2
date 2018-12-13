package com.hkdevelopment.bankapp.login.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.hkdevelopment.bankapp.R;
import com.hkdevelopment.bankapp.home.view.HomeView;
import com.hkdevelopment.bankapp.login.model.User;
import com.hkdevelopment.bankapp.login.utils.VerifyData;
import com.hkdevelopment.bankapp.login.view.LoginViewInt;
import com.hkdevelopment.bankapp.retrofit.RetrofitInstance;
import com.hkdevelopment.bankapp.retrofit.services.Services;
import com.hkdevelopment.bankapp.utils.PreferencesManager;
import com.hkdevelopment.bankapp.utils.PreferencesManagerInt;
import com.hkdevelopment.bankapp.utils.ProgressManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginPresenterInt{

    private Context context;
    private LoginViewInt view;
    private VerifyData verifyData;
    private PreferencesManagerInt prefs;
    private ProgressManager pgManger;

    public LoginPresenter(Context context, LoginViewInt view,PreferencesManagerInt prefs){
        this.context=context;
        this.view=view;
        verifyData=new VerifyData();
        this.prefs=new PreferencesManager(context);
        pgManger=new ProgressManager(context);
    }

    public void verifyData(String user, String password){
        pgManger.showDialog("Carregando...");
        if(user.isEmpty()) {
            view.setUserError(context.getString(R.string.user_empty));
            pgManger.purgeDialog();
        }else if(password.isEmpty()) {
            view.setPassError(context.getString(R.string.pass_empty));
            pgManger.purgeDialog();
        }else if(!verifyData.isCpf(user) && !verifyData.isEmail(user)) {
            view.setUserError(context.getString(R.string.user_error));
            pgManger.purgeDialog();
        }else if(!verifyData.isPass(password)) {
            view.setPassError(context.getString(R.string.pass_error));
            pgManger.purgeDialog();
        }else {
            doLogin(user, password);
        }
    }

    @Override
    public void doLogin(final String user, final String password) {
        Services service = RetrofitInstance.getRetrofitInstance().create(Services.class);
        Call<User> call = service.getUser(encodeFields(user), encodeFields(password));
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.isSuccessful()){
                    prefs.putString(context.getString(R.string.user),user);

                    prefs.putString(context.getString(R.string.password),password);

                    Log.d("teste,PREFS",user+"/"+password);
                    Intent i=new Intent(context,HomeView.class);
                    assert response.body() != null;
                    i.putExtra("userPojo",response.body().getUserAccount());
                    context.startActivity(i);
                    pgManger.purgeDialog();
                }
                pgManger.purgeDialog();
            }
            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Log.d("ErroLogin",t.getMessage());
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                pgManger.purgeDialog();
            }
        });
    }

    @Override
    public String encodeFields(String msg) {
        try {
            return URLEncoder.encode(msg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            pgManger.purgeDialog();
            return "Erro";
        }
    }
}

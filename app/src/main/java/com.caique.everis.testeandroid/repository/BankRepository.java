package com.caique.everis.testeandroid.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.caique.everis.testeandroid.data.local.AppDataBase;
import com.caique.everis.testeandroid.data.remote.CallApi;
import com.caique.everis.testeandroid.data.remote.RetrofitClientInstance;
import com.caique.everis.testeandroid.model.AccountInfoResponse;
import com.caique.everis.testeandroid.model.Login;
import com.caique.everis.testeandroid.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BankRepository {
    private static BankRepository instance;
    private Retrofit retrofit;
    private static final String HEADER = "application/x-www-form-urlencoded";
    private AppDataBase appDataBase;

    private BankRepository(AppDataBase appDataBase){
        this.appDataBase = appDataBase;
        retrofit = RetrofitClientInstance.getRetrofitInstance();
    }

    public static BankRepository getInstance(AppDataBase appDataBase){
        if (instance == null) {
            synchronized (BankRepository.class) {
                if (instance == null) {
                    instance = new BankRepository(appDataBase);
                }
            }
        }
        return instance;
    }

    public LiveData<Login> getLoginDao(){
        return appDataBase.loginDao().getLoginDao();
    }

    public LiveData<LoginResponse> getLoginResponse(final Login login) {
        CallApi callApi = retrofit.create(CallApi.class);
        final MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();

        callApi.getLogin(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                appDataBase.loginDao().deleteAll();
                appDataBase.loginDao().save(login);
                loginResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseMutableLiveData.setValue(null);
            }
        });

        return loginResponseMutableLiveData;
    }

    public LiveData<AccountInfoResponse> getAccountInfoResponse() {
        CallApi callApi = retrofit.create(CallApi.class);
        final MutableLiveData<AccountInfoResponse> accountInfoResponseMutableLiveData = new MutableLiveData<>();
        callApi.getAccountInfo(HEADER).enqueue(new Callback<AccountInfoResponse>() {
            @Override
            public void onResponse(Call<AccountInfoResponse> call, Response<AccountInfoResponse> response) {
                accountInfoResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AccountInfoResponse> call, Throwable t) {
                accountInfoResponseMutableLiveData.setValue(null);
            }
        });
        return accountInfoResponseMutableLiveData;
    }
}

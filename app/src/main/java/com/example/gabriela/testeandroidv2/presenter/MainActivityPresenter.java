package com.example.gabriela.testeandroidv2.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriela.testeandroidv2.interfaces.IRetrofitCEP;
import com.example.gabriela.testeandroidv2.model.LoginRes;
import com.example.gabriela.testeandroidv2.view.contact.MainActivityInterface;
import com.example.gabriela.testeandroidv2.view.ui.InfoActivity;
import com.example.gabriela.testeandroidv2.view.ui.MainActivity;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    Activity activity;
    MainActivityInterface view;
    String nome, conta, saldo, user, senha;

    public MainActivityPresenter(Activity activity, MainActivityInterface view) {
        this.activity = activity;
        this.view = view;
    }

    public void login(EditText editUser, EditText editSenha) {

        if(editUser.length() == 0){
            editUser.requestFocus();
            editUser.setError("Ops! Você esqueceu de digitar um CPF ou nome de usuário");
            return;
        }else if(editSenha.length() == 0){
            editSenha.requestFocus();
            editSenha.setError("Ops! Você esqueceu de digitar a senha");
            return;
        }else{
            user = editUser.getText().toString();
            senha = editSenha.getText().toString();
            view.showProgress();

            IRetrofitCEP doLogin = IRetrofitCEP.retrofit.create(IRetrofitCEP.class);
            final Call<LoginRes> call = doLogin.doLogin(user, senha);

            call.enqueue(new Callback<LoginRes>() {
                @Override
                public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                    view.hideProgress();
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        LoginRes loginRes = response.body();
                        nome = loginRes.userAccount.name;
                        conta = loginRes.userAccount.agency +"/"+ loginRes.userAccount.bankAccount;
                        saldo = loginRes.userAccount.balance;
                        view.redirectActivity(activity, InfoActivity.class, nome, conta, saldo);



                    }else {
                        view.showToast("Ops! Error code "+response.code());
                        Log.e("ERRORLOGIN", String.valueOf(response.code()));
                    }

                }

                @Override
                public void onFailure(Call<LoginRes> call, Throwable t) {
                    view.hideProgress();
                    view.showToast("Ops! Erro ao realizar login, verifique sua internet");
                }
            });
        }



    }
}

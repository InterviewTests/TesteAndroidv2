package com.example.savioguedes.testeandroid.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.savioguedes.testeandroid.model.Login;
import com.example.savioguedes.testeandroid.model.ResponseLogin;
import com.example.savioguedes.testeandroid.model.UserAccount;
import com.example.savioguedes.testeandroid.service.Api;
import com.example.savioguedes.testeandroid.service.Manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;
    private Context context;

    LoginPresenter(LoginContract.View view, Context context){

        this.view = view;
        this.context = context;

        view.initView();
    }

    @Override
    public void getLoginExecute(final Login login) {

        Api api = Manager.serviceGenerator().create(Api.class);
        Call<ResponseLogin> call = api.login(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (!response.isSuccessful()){

                    view.onErroRequest();
                    Log.i("RESPOSTA_REQUEST_LOGIN", "Erro: " + response + " - Code: " + response.code());
                }
                else {

                    if (response.body() != null) {

                        UserAccount userAccount = response.body().getUserAccount();
                        setPreferences(login.getUser(), "CURRENT_USERNAME");

                        view.passUserinfo(userAccount.getUserId(), userAccount.getName(),
                                userAccount.getBankAccount()+" / "+userAccount.getAgency(),
                                String.valueOf(userAccount.getBalance()));

                        Log.i("RESPOSTA_REQUEST_LOGIN", "Sucesso: " + userAccount.getBankAccount());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                view.onErroRequest();
                Log.i("RESPOSTA_REQUEST_LOGIN", "Falha: " +t.getMessage());
            }
        });
    }

    @Override
    public void setPreferences(String value, String tag) {

        SharedPreferences.Editor editor = context.getSharedPreferences("LOCAL_SAVE", Context.MODE_PRIVATE).edit();
        editor.putString(tag, value);
        editor.apply();
    }

    @Override
    public boolean isValidFields(String user, String password) {

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher passwordMatcher = pattern.matcher(password);

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)){

            Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
            return false;
        }
        else{

            if(android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches() && passwordMatcher.matches()){

                return true;
            }
            else{

                Toast.makeText(context, "Usuario ou senha inválidos", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    @Override
    public String getPreferences(String tag) {

        String prefs = context.getSharedPreferences("LOCAL_SAVE", Context.MODE_PRIVATE)
                .getString(tag,"");

        return prefs;
    }

}

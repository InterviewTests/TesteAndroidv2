package monteoliva.testbank.controller;


import android.support.annotation.NonNull;

import com.google.gson.Gson;

import monteoliva.testbank.utils.http.HttpPost;
import monteoliva.testbank.utils.http.HttpResultBean;

public class RestLogin {
    private OnRestLoginListener listener;

    public void send(@NonNull String user, @NonNull String password) {
        HttpPost httpPost = new HttpPost();
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cache-Control", "no-cache");

//      final String jsonBody = "user=test_user&password=Test@1";
        final String jsonBody = "user="+user+"&password="+password;

        HttpResultBean resultBean = httpPost.send("https://bank-app-test.herokuapp.com/api/login", jsonBody);

        LoginAccount result = new Gson().fromJson(resultBean.getMessage(), LoginAccount.class);

        if (result != null) {
            if (listener != null) { listener.onSuccess(result.getUserAccount()); }
        }
        else {
            if (listener != null) { listener.onError(); }
        }
    }

    public void setOnRestLoginListener(OnRestLoginListener listener) { this.listener = listener; }
}
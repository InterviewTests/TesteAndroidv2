package monteoliva.testbank.controller;


import com.google.gson.Gson;

import java.util.List;

import monteoliva.testbank.utils.http.HttpGet;
import monteoliva.testbank.utils.http.HttpResultBean;

public class RestStatement {
    private OnRestStatementListener listener;

    public void send(int id) {
        HttpGet httpGet = new HttpGet();
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpGet.addHeader("Cache-Control", "no-cache");

        final String url = "https://bank-app-test.herokuapp.com/api/statements/" + id;

        HttpResultBean resultBean = httpGet.send(url);

        Statement result = new Gson().fromJson(resultBean.getMessage(), Statement.class);

        if (result != null) {
            if (listener != null) { listener.onSuccess(result.getStatementList()); }
        }
        else {
            if (listener != null) { listener.onError(); }
        }
    }

    public void setOnRestStatementListener(OnRestStatementListener listener) { this.listener = listener; }
}
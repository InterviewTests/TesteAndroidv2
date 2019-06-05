package com.example.gabriela.testeandroidv2.view.contact;

import android.content.Context;

public interface MainActivityInterface {

    void showToast(String msg);
    void redirectActivity(Context context, Class<?> willgo, String nome, String conta, String saldo);
    void showProgress();
    void hideProgress();
}

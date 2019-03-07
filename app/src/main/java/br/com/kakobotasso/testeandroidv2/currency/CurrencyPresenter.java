package br.com.kakobotasso.testeandroidv2.currency;

import android.content.Context;

import java.lang.ref.WeakReference;

interface CurrencyPresenterInput {
    void showCurrencyInfo(CurrencyResponse response);

    Context getContext();
}

public class CurrencyPresenter implements CurrencyPresenterInput {
    public WeakReference<CurrencyActivityInput> output;

    @Override
    public void showCurrencyInfo(CurrencyResponse response) {
        output.get().showCurrencyInfo(response);
    }

    @Override
    public Context getContext() {
        CurrencyActivity activity = (CurrencyActivity) output.get();
        return activity;
    }
}

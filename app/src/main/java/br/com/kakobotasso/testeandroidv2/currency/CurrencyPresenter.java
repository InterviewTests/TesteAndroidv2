package br.com.kakobotasso.testeandroidv2.currency;

import java.lang.ref.WeakReference;

interface CurrencyPresenterInput {
    void showCurrencyInfo(CurrencyResponse response);
}

public class CurrencyPresenter implements CurrencyPresenterInput {
    public WeakReference<CurrencyActivityInput> output;

    @Override
    public void showCurrencyInfo(CurrencyResponse response) {
        output.get().showCurrencyInfo(response);
    }
}

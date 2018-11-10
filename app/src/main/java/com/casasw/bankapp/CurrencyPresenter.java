package com.casasw.bankapp;

import java.lang.ref.WeakReference;

interface CurrencyPresenterInput {
    public void presentCurrencyData(CurrencyResponse response);
}


public class CurrencyPresenter implements CurrencyPresenterInput {

    public static String TAG = CurrencyPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<CurrencyActivityInput> output;


    @Override
    public void presentCurrencyData(CurrencyResponse response) {
        // Log.e(TAG, "presentCurrencyData() called with: response = [" + response + "]");
        //Do your decoration or filtering here

    }


}

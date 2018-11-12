package com.casasw.bankapp;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

interface CurrencyInteractorInput {
    void fetchCurrencyData(CurrencyRequest request);
}


public class CurrencyInteractor implements CurrencyInteractorInput {

    public static String TAG = CurrencyInteractor.class.getSimpleName();
    public CurrencyPresenterInput output;
    private CurrencyWorkerInput aCurrencyWorkerInput;

    private CurrencyWorkerInput getCurrencyWorkerInput() {
        if (aCurrencyWorkerInput == null) return new CurrencyWorker();
        return aCurrencyWorkerInput;
    }

    void setCurrencyWorkerInput(CurrencyWorkerInput aCurrencyWorkerInput) {
        this.aCurrencyWorkerInput = aCurrencyWorkerInput;
    }

    @Override
    public void fetchCurrencyData(CurrencyRequest request) {

        Ion.with(request.getmContext())
                .load("https://bank-app-test.herokuapp.com/api/statements/"+request.getMLoginViewModel().getUserId())
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        CurrencyResponse currencyResponse = new CurrencyResponse();
                        currencyResponse.setCurrencyJSON(result);
                        output.presentCurrencyData(currencyResponse);

                    }
                });
    }
}

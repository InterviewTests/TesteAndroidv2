package com.casasw.bankapp;

interface CurrencyInteractorInput {
    public void fetchCurrencyData(CurrencyRequest request);
}


public class CurrencyInteractor implements CurrencyInteractorInput {

    public static String TAG = CurrencyInteractor.class.getSimpleName();
    public CurrencyPresenterInput output;
    public CurrencyWorkerInput aCurrencyWorkerInput;

    public CurrencyWorkerInput getCurrencyWorkerInput() {
        if (aCurrencyWorkerInput == null) return new CurrencyWorker();
        return aCurrencyWorkerInput;
    }

    public void setCurrencyWorkerInput(CurrencyWorkerInput aCurrencyWorkerInput) {
        this.aCurrencyWorkerInput = aCurrencyWorkerInput;
    }

    @Override
    public void fetchCurrencyData(CurrencyRequest request) {
        aCurrencyWorkerInput = getCurrencyWorkerInput();
        CurrencyResponse CurrencyResponse = new CurrencyResponse();
        // Call the workers

        output.presentCurrencyData(CurrencyResponse);
    }
}

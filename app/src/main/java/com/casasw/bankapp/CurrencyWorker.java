package com.casasw.bankapp;

interface CurrencyWorkerInput {
    public String getCurrencyData();
}

public class CurrencyWorker implements CurrencyWorkerInput {

    @Override
    public String getCurrencyData() {
        return "dummy";
    }
}

package com.example.santanderapplication.ui.transactions;

public class BankCurrencyPresenter implements BankCurrencyContract.StatementPresenter {

    private BankCurrencyContract.StatementView view;

    public BankCurrencyPresenter(BankCurrencyContract.StatementView view) {
        this.view = view;
    }

    public BankCurrencyPresenter() {

    }
}

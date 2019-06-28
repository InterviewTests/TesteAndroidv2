package com.example.santandertestebank.ui;

public class BankPaymentsPresenter implements BankPaymentsContract.Presenter {

    private BankPaymentsContract.View view;

    public BankPaymentsPresenter(BankPaymentsContract.View view) {
        this.view = view;
    }
}

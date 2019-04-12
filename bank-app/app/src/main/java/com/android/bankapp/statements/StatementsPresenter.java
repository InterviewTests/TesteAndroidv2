package com.android.bankapp.statements;

import com.android.bankapp.service.BankService;
import com.android.bankapp.service.ServiceGenerator;

import java.lang.ref.WeakReference;

class StatementsPresenter implements StatementPresenterInput {

    WeakReference<StatementsActivity> output;
    private BankService service;

    public StatementsPresenter() {
        service = ServiceGenerator.generateService(BankService.class);
    }

    @Override
    public void loadData() {

        

    }
}

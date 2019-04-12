package com.android.bankapp.statements;

import java.lang.ref.WeakReference;

public enum StatementConfigurator {

    INSTANCE;

    public void configure(StatementsActivity activity){

        StatementsPresenter presenter = new StatementsPresenter();
        presenter.output = new WeakReference<>(activity);

        StatementInteractor interactor = new StatementInteractor();
        interactor.output = presenter;

        if (activity.output == null){
            activity.output = interactor;
        }

    }

}

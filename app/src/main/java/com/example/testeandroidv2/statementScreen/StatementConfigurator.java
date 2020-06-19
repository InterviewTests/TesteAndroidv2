package com.example.testeandroidv2.statementScreen;

import java.lang.ref.WeakReference;

public enum StatementConfigurator {
    INSTANCE;

    public void configure(StatementActivity activity) {
        StatementPresenter presenter = new StatementPresenter();
        presenter.output = new WeakReference<>(activity);

        StatementInteractor interactor = new StatementInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
    }
}

package com.casasw.bankapp;

import java.lang.ref.WeakReference;


public enum CurrencyConfigurator {
    INSTANCE;

    public void configure(CurrencyActivity activity) {

        CurrencyRouter router = new CurrencyRouter();
        router.activity = new WeakReference<>(activity);

        CurrencyPresenter presenter = new CurrencyPresenter();
        presenter.output = new WeakReference<CurrencyActivityInput>(activity);

        CurrencyInteractor interactor = new CurrencyInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}

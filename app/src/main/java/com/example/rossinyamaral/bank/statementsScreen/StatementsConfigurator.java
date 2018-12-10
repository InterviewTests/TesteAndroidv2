package com.example.rossinyamaral.bank.statementsScreen;

import java.lang.ref.WeakReference;


public enum StatementsConfigurator {
    INSTANCE;

    public void configure(StatementsActivity activity) {

        StatementsRouter router = new StatementsRouter();
        router.activity = new WeakReference<>(activity);

        StatementsPresenter presenter = new StatementsPresenter();
        presenter.output = new WeakReference<StatementsActivityInput>(activity);

        StatementsInteractor interactor = new StatementsInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}

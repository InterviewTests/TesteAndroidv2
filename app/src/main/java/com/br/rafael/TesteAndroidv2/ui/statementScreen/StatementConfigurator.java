package com.br.rafael.TesteAndroidv2.ui.statementScreen;

import java.lang.ref.WeakReference;

public enum StatementConfigurator {
    INSTANCE;

    public void configure(StatementActivity activity) {

        StatementRouter router = new StatementRouter();
        router.activity = new WeakReference<>(activity);

        StatementPresenter presenter = new StatementPresenter();
        presenter.output = new WeakReference<StatementActivityInput>(activity);

        StatementIntaractor interactor = new StatementIntaractor();
        interactor.output = presenter;

        if (activity.output == null){
            activity.output = interactor;
        }
        if (activity.router == null){
            activity.router = router;
        }
    }
}

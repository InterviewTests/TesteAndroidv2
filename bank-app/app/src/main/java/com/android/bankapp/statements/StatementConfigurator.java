package com.android.bankapp.statements;

import com.android.bankapp.statements.interactor.StatementInteractor;
import com.android.bankapp.statements.presenter.StatementsPresenter;
import com.android.bankapp.statements.router.StatementRouter;
import com.android.bankapp.statements.view.StatementsActivity;

import java.lang.ref.WeakReference;

public enum StatementConfigurator {

    INSTANCE;

    public void configure(StatementsActivity activity) {

        StatementsPresenter presenter = new StatementsPresenter();
        presenter.output = new WeakReference<>(activity);

        StatementInteractor interactor = new StatementInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }

        if (activity.router == null) {
            activity.router = new StatementRouter();
        }

    }

}

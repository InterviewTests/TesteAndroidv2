package com.desafiosantander.DashBoard;

import java.lang.ref.WeakReference;


public enum DashBoardConfigurator {
    INSTANCE;

    public void configure(DashBoardActivity activity) {

        DashBoardRouter router = new DashBoardRouter();
        router.activity = new WeakReference<>(activity);

        DashBoardPresenter presenter = new DashBoardPresenter();
        presenter.output = new WeakReference<DashBoardActivityInput>(activity);

        DashBoardInteractor interactor = new DashBoardInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}

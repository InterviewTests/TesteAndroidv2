package com.avanade.testesantander2.homeScreen;

import java.lang.ref.WeakReference;

public enum HomeConfigurator {
    INSTANCE;

    public void configure(HomeActivity activity) {

        HomeRouter router = new HomeRouter();
        router.activity = new WeakReference<>(activity);

        HomePresenter presenter = new HomePresenter();
        presenter.output = new WeakReference<HomeActivityInput>(activity);

        HomeInteractor interactor = new HomeInteractor();
        interactor.output = presenter;

        if (activity.output == null)
            activity.output = interactor;

        if (activity.router == null)
            activity.router = router;

    }
}


package ssilvalucas.testeandroidv2.screen.home;

import java.lang.ref.WeakReference;

public enum HomeConfigurator {
    INSTANCE;

    public void configure(HomeActivity homeActivity) {

        HomeRouter router = new HomeRouter();
        router.activity = new WeakReference<>(homeActivity);

        HomePresenter presenter = new HomePresenter();
        presenter.output = new WeakReference<>(homeActivity);

        HomeInteractor interactor = new HomeInteractor();
        interactor.output = presenter;

        if(homeActivity.output == null){
            homeActivity.output = interactor;
        }
        if(homeActivity.router == null){
            homeActivity.router = router;
        }
    }

}

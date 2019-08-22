package teste.santander.com.santander;

import java.lang.ref.WeakReference;

public enum MainConfigurator {
    INSTANCE;
    public void configure(MainActivity activity){

        MainRouter router = new MainRouter();
        router.activity = new WeakReference<>(activity);

        MainPresenter presenter = new MainPresenter();
        presenter.output = new WeakReference<MainActivityInput>(activity);

        MainInteractor interactor = new MainInteractor();
        interactor.output = presenter;
        interactor.activity = new WeakReference<>(activity);

        if (activity.output == null){
            activity.output = interactor;
        }

    }
}

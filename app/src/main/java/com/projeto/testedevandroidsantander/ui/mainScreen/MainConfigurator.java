package com.projeto.testedevandroidsantander.ui.mainScreen;

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

        LancamentoWorker lancamentoWorker = new LancamentoWorker();
        lancamentoWorker.interactor = interactor;
        interactor.lancamentoWorkerInput = lancamentoWorker;

        if (activity.output == null){
            activity.output = interactor;
        }
        if (activity.router == null){
            activity.router = router;
        }
    }
}

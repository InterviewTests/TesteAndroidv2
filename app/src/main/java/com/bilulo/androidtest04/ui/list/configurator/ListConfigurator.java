package com.bilulo.androidtest04.ui.list.configurator;

import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.interactor.ListInteractor;
import com.bilulo.androidtest04.ui.list.presenter.ListPresenter;
import com.bilulo.androidtest04.ui.list.router.ListRouter;
import com.bilulo.androidtest04.ui.list.view.ListActivity;
import com.bilulo.androidtest04.ui.list.worker.ListWorker;

import java.lang.ref.WeakReference;

public class ListConfigurator {
    public static void configure(ListActivity activity) {
        ListRouter router = new ListRouter();
        router.activity = new WeakReference<>(activity);

        ListPresenter presenter = new ListPresenter();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(activity);

        ListInteractor interactor = new ListInteractor();
        interactor.presenter = presenter;

        ListWorker listWorker = new ListWorker();
        listWorker.interactor = interactor;
        interactor.worker = listWorker;

        if (activity.interactor == null) {
            activity.interactor = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}

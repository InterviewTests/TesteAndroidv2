package com.bilulo.androidtest04.ui.list.configurator;

import com.bilulo.androidtest04.ui.list.contract.ListContract;
import com.bilulo.androidtest04.ui.list.interactor.ListInteractor;
import com.bilulo.androidtest04.ui.list.presenter.ListPresenter;
import com.bilulo.androidtest04.ui.list.view.ListActivity;

import java.lang.ref.WeakReference;

public class ListConfigurator {
    public static void configure(ListActivity activity) {
        ListPresenter presenter = new ListPresenter();
        presenter.activity = new WeakReference<ListContract.ActivityContract>(activity);

        ListInteractor interactor = new ListInteractor();
        interactor.presenter = presenter;

        if (activity.interactor == null) {
            activity.interactor = interactor;
        }
    }
}

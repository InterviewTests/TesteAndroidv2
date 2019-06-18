package com.bilulo.androidtest04.ui.list.presenter;

import com.bilulo.androidtest04.ui.list.contract.ListContract;

import java.lang.ref.WeakReference;

public class ListPresenter implements ListContract.PresenterContract {
    public WeakReference<ListContract.ActivityContract> activity;

}
